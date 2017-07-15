package de.invesdwin.context.matlab.runtime.matlabconsolectl;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.integration.script.IScriptTaskEngine;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;

@NotThreadSafe
public class MatlabConsoleCtlScriptTaskEngineMatlab implements IScriptTaskEngine {

    private MatlabProxy matlabProxy;
    private final MatlabConsoleCtlScriptTaskInputsMatlab inputs;
    private final MatlabConsoleCtlScriptTaskResultsMatlab results;
    private String expressionEnding;

    public MatlabConsoleCtlScriptTaskEngineMatlab(final MatlabProxy octaveEngine) {
        this.matlabProxy = octaveEngine;
        this.inputs = new MatlabConsoleCtlScriptTaskInputsMatlab(this);
        this.results = new MatlabConsoleCtlScriptTaskResultsMatlab(this);
        if (IScriptTaskRunnerMatlab.LOG.isDebugEnabled()) {
            expressionEnding = ";";
        } else {
            expressionEnding = "";
        }
    }

    @Override
    public void eval(final String expression) {
        try {
            matlabProxy.eval(expression + expressionEnding);
        } catch (final MatlabInvocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public MatlabConsoleCtlScriptTaskInputsMatlab getInputs() {
        return inputs;
    }

    @Override
    public MatlabConsoleCtlScriptTaskResultsMatlab getResults() {
        return results;
    }

    @Override
    public void close() {
        matlabProxy = null;
    }

    @Override
    public MatlabProxy unwrap() {
        return matlabProxy;
    }

}
