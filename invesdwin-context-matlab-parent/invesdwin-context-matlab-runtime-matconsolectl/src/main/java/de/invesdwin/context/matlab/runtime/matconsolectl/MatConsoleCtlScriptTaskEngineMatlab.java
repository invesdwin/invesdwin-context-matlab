package de.invesdwin.context.matlab.runtime.matconsolectl;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.integration.script.IScriptTaskEngine;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.matlab.runtime.matconsolectl.pool.MatlabProxyObjectPool;
import de.invesdwin.util.concurrent.lock.ILock;
import de.invesdwin.util.concurrent.lock.disabled.DisabledLock;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.extensions.MatlabTypeConverter;

@NotThreadSafe
public class MatConsoleCtlScriptTaskEngineMatlab implements IScriptTaskEngine {

    private MatlabProxy matlabProxy;
    private MatlabTypeConverter typeConverter;
    private final MatConsoleCtlScriptTaskInputsMatlab inputs;
    private final MatConsoleCtlScriptTaskResultsMatlab results;
    private String expressionEnding;

    public MatConsoleCtlScriptTaskEngineMatlab(final MatlabProxy matlabProxy) {
        this.matlabProxy = matlabProxy;
        this.typeConverter = new MatlabTypeConverter(matlabProxy);
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
        typeConverter = null;
    }

    @Override
    public MatlabProxy unwrap() {
        return matlabProxy;
    }

    public MatlabTypeConverter getTypeConverter() {
        return typeConverter;
    }

    public int pos2ind(final int[] size, final int... pos) {
        if (size.length != pos.length) {
            throw new IllegalArgumentException("There must be an equal number of lengths [" + size.length + "] "
                    + "and indices [" + pos.length + "]");
        }

        int linearIndex = 0;

        int accumSize = 1;
        for (int i = 0; i < size.length; i++) {
            linearIndex += accumSize * pos[i];
            accumSize *= size[i];
        }

        return linearIndex;
    }

    /**
     * Each instance has its own engine, so no shared locking required.
     */
    @Override
    public ILock getSharedLock() {
        return DisabledLock.INSTANCE;
    }

    public static MatConsoleCtlScriptTaskEngineMatlab newInstance() {
        return new MatConsoleCtlScriptTaskEngineMatlab(MatlabProxyObjectPool.INSTANCE.borrowObject()) {
            @Override
            public void close() {
                final MatlabProxy unwrap = unwrap();
                if (unwrap != null) {
                    MatlabProxyObjectPool.INSTANCE.returnObject(unwrap);
                }
                super.close();
            }
        };
    }

}
