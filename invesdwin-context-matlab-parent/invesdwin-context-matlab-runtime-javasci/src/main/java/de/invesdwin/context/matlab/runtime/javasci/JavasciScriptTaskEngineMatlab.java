package de.invesdwin.context.matlab.runtime.javasci;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.annotation.concurrent.NotThreadSafe;

import org.apache.commons.io.FileUtils;
import org.scilab.modules.javasci.Scilab;

import de.invesdwin.context.ContextProperties;
import de.invesdwin.context.integration.script.IScriptTaskEngine;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.util.lang.UniqueNameGenerator;

@NotThreadSafe
public class JavasciScriptTaskEngineMatlab implements IScriptTaskEngine {

    private static final UniqueNameGenerator UNIQUE_NAME_GENERATOR = new UniqueNameGenerator() {
        @Override
        protected long getInitialValue() {
            return 1;
        }
    };
    private static final File FOLDER = new File(ContextProperties.TEMP_DIRECTORY,
            JavasciScriptTaskEngineMatlab.class.getSimpleName());

    private Scilab scilab;
    private final JavasciScriptTaskInputsMatlab inputs;
    private final JavasciScriptTaskResultsMatlab results;
    private String expressionEnding;
    private File scriptFile;

    public JavasciScriptTaskEngineMatlab(final Scilab scilab) {
        this.scilab = scilab;
        this.inputs = new JavasciScriptTaskInputsMatlab(this);
        this.results = new JavasciScriptTaskResultsMatlab(this);
        if (IScriptTaskRunnerMatlab.LOG.isDebugEnabled()) {
            expressionEnding = ";";
        } else {
            expressionEnding = "";
        }
        try {
            FileUtils.forceMkdir(FOLDER);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        this.scriptFile = new File(FOLDER, UNIQUE_NAME_GENERATOR.get("script") + ".sce");
    }

    @Override
    public void eval(final String expression) {
        try {
            FileUtils.writeStringToFile(scriptFile, expression + expressionEnding, Charset.defaultCharset());
            scilab.execException(scriptFile);
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JavasciScriptTaskInputsMatlab getInputs() {
        return inputs;
    }

    @Override
    public JavasciScriptTaskResultsMatlab getResults() {
        return results;
    }

    @Override
    public void close() {
        eval(JavasciScriptTaskRunnerMatlab.CLEANUP_SCRIPT);
        FileUtils.deleteQuietly(scriptFile);
        scriptFile = null;
        scilab = null;
    }

    @Override
    public Scilab unwrap() {
        return scilab;
    }

}
