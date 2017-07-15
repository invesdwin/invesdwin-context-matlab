package de.invesdwin.context.matlab.runtime.matconsolectl;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.integration.script.IScriptTaskEngine;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;

@NotThreadSafe
public class MatConsoleCtlScriptTaskEngineMatlab implements IScriptTaskEngine {

    private MatlabProxy matlabProxy;
    private final MatConsoleCtlScriptTaskInputsMatlab inputs;
    private final MatConsoleCtlScriptTaskResultsMatlab results;
    private String expressionEnding;

    public MatConsoleCtlScriptTaskEngineMatlab(final MatlabProxy octaveEngine) {
        this.matlabProxy = octaveEngine;
        this.inputs = new MatConsoleCtlScriptTaskInputsMatlab(this);
        this.results = new MatConsoleCtlScriptTaskResultsMatlab(this);
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
    public MatConsoleCtlScriptTaskInputsMatlab getInputs() {
        return inputs;
    }

    @Override
    public MatConsoleCtlScriptTaskResultsMatlab getResults() {
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
