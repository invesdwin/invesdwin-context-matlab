package de.invesdwin.context.matlab.runtime.javasci.callback.socket;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.concurrent.loop.ASpinWait;
import de.invesdwin.util.error.Throwables;
import de.invesdwin.util.lang.Files;
import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
public class FileScriptTaskCallbackServerHandler implements Runnable {

    private final FileScriptTaskCallbackContext callbackContext;
    private final ASpinWait requestSpinWait;

    public FileScriptTaskCallbackServerHandler(final FileScriptTaskCallbackContext callbackContext) {
        this.callbackContext = callbackContext;
        this.requestSpinWait = new ASpinWait() {

            @Override
            public boolean isConditionFulfilled() throws Exception {
                return callbackContext.getRequestDoneFile().exists();
            }
        };
    }

    @Override
    public void run() {
        try {
            while (true) {
                requestSpinWait.awaitFulfill(System.nanoTime());
                final String request = Files.readFileToString(callbackContext.getRequestFile(),
                        Charset.defaultCharset());
                Files.deleteQuietly(callbackContext.getRequestFile());
                Files.deleteQuietly(callbackContext.getRequestDoneFile());
                final String response = handle(request);
                Files.writeStringToFile(callbackContext.getResponseFile(), response, Charset.defaultCharset());
                Files.touchQuietly(callbackContext.getResponseDoneFile());
            }
        } catch (final Throwable t) {
            if (!Throwables.isCausedByInterrupt(t)) {
                throw Throwables.propagate(t);
            }
        }
    }

    private String handle(final String input) throws IOException {
        final int dimsEndIndex = Strings.indexOf(input, ";");
        if (dimsEndIndex <= 0) {
            throw new IllegalArgumentException(
                    "requiring request format [<dims>;<args>] where first arg is methodName: " + input);
        }
        final String dims = input.substring(0, dimsEndIndex);
        final String args = input.substring(dimsEndIndex + 1, input.length());
        return callbackContext.invoke(dims, args);
    }

}
