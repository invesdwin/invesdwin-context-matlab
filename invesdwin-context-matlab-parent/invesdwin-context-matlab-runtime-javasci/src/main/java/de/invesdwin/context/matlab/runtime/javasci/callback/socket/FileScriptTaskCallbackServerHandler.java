package de.invesdwin.context.matlab.runtime.javasci.callback.socket;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
public class FileScriptTaskCallbackServerHandler implements Runnable {

    private FileScriptTaskCallbackContext callbackContext;

    @Override
    public void run() {}

    private String handle(final String input) throws IOException {
        if (callbackContext == null) {
            callbackContext = FileScriptTaskCallbackContext.getContext(input);
            if (callbackContext == null) {
                throw new IllegalArgumentException(
                        FileScriptTaskCallbackContext.class.getSimpleName() + " not found for uuid: " + input);
            }
            return null;
        }
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
