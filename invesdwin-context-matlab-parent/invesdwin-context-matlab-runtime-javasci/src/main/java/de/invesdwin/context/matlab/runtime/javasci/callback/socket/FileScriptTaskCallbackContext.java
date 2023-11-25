package de.invesdwin.context.matlab.runtime.javasci.callback.socket;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;

import javax.annotation.concurrent.ThreadSafe;

import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.NullNode;

import de.invesdwin.context.ContextProperties;
import de.invesdwin.context.integration.marshaller.MarshallerJsonJackson;
import de.invesdwin.context.integration.script.IScriptTaskEngine;
import de.invesdwin.context.integration.script.callback.IScriptTaskCallback;
import de.invesdwin.context.log.error.Err;
import de.invesdwin.context.log.error.LoggedRuntimeException;
import de.invesdwin.context.matlab.runtime.contract.callback.ScriptTaskParametersMatlabFromJson;
import de.invesdwin.context.matlab.runtime.contract.callback.ScriptTaskParametersMatlabFromJsonPool;
import de.invesdwin.context.matlab.runtime.contract.callback.ScriptTaskReturnsMatlabToExpression;
import de.invesdwin.context.matlab.runtime.contract.callback.ScriptTaskReturnsMatlabToExpressionPool;
import de.invesdwin.util.concurrent.Executors;
import de.invesdwin.util.concurrent.WrappedExecutorService;
import de.invesdwin.util.error.Throwables;
import de.invesdwin.util.lang.Files;
import de.invesdwin.util.lang.UUIDs;
import de.invesdwin.util.lang.string.Strings;

@ThreadSafe
public class FileScriptTaskCallbackContext implements Closeable {

    private static final File DIRECTORY = new File(ContextProperties.TEMP_DIRECTORY,
            FileScriptTaskCallbackContext.class.getSimpleName());

    private final String uuid;
    private final IScriptTaskCallback callback;
    private final ObjectMapper mapper;
    private final File requestFile;
    private final File responseFile;
    private final File requestDoneFile;
    private final File responseDoneFile;
    private final WrappedExecutorService handlerExecutor;

    public FileScriptTaskCallbackContext(final IScriptTaskCallback callback) {
        this.uuid = UUIDs.newPseudoRandomUUID();
        this.callback = callback;
        this.mapper = MarshallerJsonJackson.getInstance().getJsonMapper(false);
        try {
            Files.forceMkdir(DIRECTORY);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        this.requestFile = new File(DIRECTORY, uuid + ".request");
        this.responseFile = new File(DIRECTORY, uuid + ".response");
        this.requestDoneFile = new File(DIRECTORY, uuid + ".request.done");
        this.responseDoneFile = new File(DIRECTORY, uuid + ".response.done");
        deleteFiles();
        this.handlerExecutor = Executors
                .newFixedThreadPool(FileScriptTaskCallbackContext.class.getSimpleName() + "_" + uuid, 1);
        this.handlerExecutor.execute(new FileScriptTaskCallbackServerHandler(this));

    }

    public void init(final IScriptTaskEngine engine) {
        engine.getInputs().putString("socketScriptTaskCallbackContextRequestFile", getRequestFile().getAbsolutePath());
        engine.getInputs()
                .putString("socketScriptTaskCallbackContextResponseFile", getResponseFile().getAbsolutePath());
        engine.getInputs()
                .putString("socketScriptTaskCallbackContextRequestDoneFile", getRequestDoneFile().getAbsolutePath());
        engine.getInputs()
                .putString("socketScriptTaskCallbackContextResponseDoneFile", getResponseDoneFile().getAbsolutePath());
        engine.eval(new ClassPathResource(FileScriptTaskCallbackContext.class.getSimpleName() + ".sce",
                FileScriptTaskCallbackContext.class));
    }

    public String getUuid() {
        return uuid;
    }

    public File getRequestFile() {
        return requestFile;
    }

    public File getResponseFile() {
        return responseFile;
    }

    public File getRequestDoneFile() {
        return requestDoneFile;
    }

    public File getResponseDoneFile() {
        return responseDoneFile;
    }

    public String invoke(final String dims, final String args) {
        final ScriptTaskParametersMatlabFromJson parameters = ScriptTaskParametersMatlabFromJsonPool.INSTANCE
                .borrowObject();
        final ScriptTaskReturnsMatlabToExpression returns = ScriptTaskReturnsMatlabToExpressionPool.INSTANCE
                .borrowObject();
        try {
            final JsonNode jsonDims = toJsonNode(dims);
            final JsonNode jsonArgs = toJsonNode(args);
            parameters.setParameters(jsonDims, jsonArgs, 1);
            final String methodName = parameters.getString(-1);
            callback.invoke(methodName, parameters, returns);
            return returns.getReturnExpression();
        } catch (final Throwable t) {
            final LoggedRuntimeException loggedError = Err.process(t);
            final String errorMessage = Strings.normalizeNewlines(Throwables.concatMessages(loggedError))
                    .replace("\n", " ")
                    .replace("\"", "\\\"");
            returns.returnExpression("error('CallbackException: " + errorMessage + "')");
            return returns.getReturnExpression();
        } finally {
            ScriptTaskReturnsMatlabToExpressionPool.INSTANCE.returnObject(returns);
            ScriptTaskParametersMatlabFromJsonPool.INSTANCE.returnObject(parameters);
        }
    }

    private JsonNode toJsonNode(final String json) {
        try {
            final JsonNode node = mapper.readTree(json);
            if (node instanceof NullNode) {
                return null;
            } else {
                return node;
            }
        } catch (final Throwable t) {
            throw Throwables.propagate(t);
        }
    }

    @Override
    public void close() {
        handlerExecutor.shutdownNow();
        deleteFiles();
    }

    private void deleteFiles() {
        Files.deleteQuietly(requestFile);
        Files.deleteQuietly(responseFile);
        Files.deleteQuietly(requestDoneFile);
        Files.deleteQuietly(responseDoneFile);
    }

}
