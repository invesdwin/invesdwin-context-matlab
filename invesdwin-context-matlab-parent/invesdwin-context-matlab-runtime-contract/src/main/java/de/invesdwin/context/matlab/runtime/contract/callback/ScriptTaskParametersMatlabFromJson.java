package de.invesdwin.context.matlab.runtime.contract.callback;

import java.io.Closeable;

import javax.annotation.concurrent.NotThreadSafe;

import com.fasterxml.jackson.databind.JsonNode;

@NotThreadSafe
public class ScriptTaskParametersMatlabFromJson extends AScriptTaskParametersMatlabFromJson implements Closeable {

    private JsonNode dims;
    private JsonNode parameters;

    public void setParameters(final JsonNode dims, final JsonNode parameters) {
        this.dims = dims;
        this.parameters = parameters;
    }

    @Override
    public int size() {
        return parameters.size();
    }

    @Override
    protected JsonNode getAsJsonNode(final int index) {
        return parameters.get(index);
    }

    @Override
    protected JsonNode getAsJsonNodeDims(final int index) {
        return dims.get(index);
    }

    @Override
    public void close() {
        dims = null;
        parameters = null;
    }

}
