package de.invesdwin.context.matlab.runtime.javasci.callback.socket;

import java.io.IOException;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.integration.channel.async.IAsynchronousHandler;
import de.invesdwin.context.integration.channel.async.IAsynchronousHandlerContext;
import de.invesdwin.util.lang.string.Strings;

@NotThreadSafe
public class SocketScriptTaskCallbackServerHandler implements IAsynchronousHandler<String, String> {

    private SocketScriptTaskCallbackContext callbackContext;

    @Override
    public String open(final IAsynchronousHandlerContext<String> context) throws IOException {
        return null;
    }

    @Override
    public String idle(final IAsynchronousHandlerContext<String> context) throws IOException {
        return null;
    }

    @Override
    public String handle(final IAsynchronousHandlerContext<String> context, final String input) throws IOException {
        if (callbackContext == null) {
            callbackContext = SocketScriptTaskCallbackContext.getContext(input);
            if (callbackContext == null) {
                throw new IllegalArgumentException(
                        SocketScriptTaskCallbackContext.class.getSimpleName() + " not found for uuid: " + input);
            }
            return null;
        }
        final int methodNameEndIndex = Strings.indexOf(input, ";");
        if (methodNameEndIndex <= 0) {
            throw new IllegalArgumentException("requiring request format [<methodName>;<dims>;<args>]: " + input);
        }
        final String methodName = input.substring(0, methodNameEndIndex);
        final String dimsAndArgs = input.substring(methodNameEndIndex + 1, input.length());
        final int dimsEndIndex = Strings.indexOf(dimsAndArgs, ";");
        if (dimsEndIndex <= 0) {
            throw new IllegalArgumentException("requiring request format [<methodName>;<dims>;<args>]: " + input);
        }
        final String dims = dimsAndArgs.substring(0, dimsEndIndex);
        final String args = dimsAndArgs.substring(dimsEndIndex + 1, dimsAndArgs.length());
        return callbackContext.invoke(methodName, dims, args);
    }

    @Override
    public void outputFinished(final IAsynchronousHandlerContext<String> context) throws IOException {}

    @Override
    public void close() throws IOException {}

}
