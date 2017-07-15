package de.invesdwin.context.matlab.runtime.matconsolectl;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.matlab.runtime.contract.IScriptTaskInputsMatlab;
import matlabcontrol.MatlabInvocationException;

@NotThreadSafe
public class MatConsoleCtlScriptTaskInputsMatlab implements IScriptTaskInputsMatlab {

    private final MatConsoleCtlScriptTaskEngineMatlab engine;

    public MatConsoleCtlScriptTaskInputsMatlab(final MatConsoleCtlScriptTaskEngineMatlab engine) {
        this.engine = engine;
    }

    @Override
    public MatConsoleCtlScriptTaskEngineMatlab getEngine() {
        return engine;
    }

    private void set(final String variable, final Object value) {
        try {
            engine.unwrap().setVariable(variable, value);
        } catch (final MatlabInvocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putString(final String variable, final String value) {
        if (value == null) {
            putNull(variable);
        } else {
            set(variable, value);
        }
    }

    @Override
    public void putStringVector(final String variable, final String[] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            set(variable, value);
        }
    }

    @Override
    public void putStringMatrix(final String variable, final String[][] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            set(variable, value);
        }
    }

    @Override
    public void putDouble(final String variable, final double value) {
        set(variable, value);
    }

    @Override
    public void putDoubleVector(final String variable, final double[] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            set(variable, value);
        }
    }

    @Override
    public void putDoubleMatrix(final String variable, final double[][] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            set(variable, value);
        }
    }

    @Override
    public void putInteger(final String variable, final int value) {
        try {
            engine.unwrap().setVariable(variable, value);
        } catch (final MatlabInvocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putIntegerVector(final String variable, final int[] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            set(variable, value);
        }
    }

    @Override
    public void putIntegerMatrix(final String variable, final int[][] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            set(variable, value);
        }
    }

    @Override
    public void putBoolean(final String variable, final boolean value) {
        try {
            engine.unwrap().setVariable(variable, value);
        } catch (final MatlabInvocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void putBooleanVector(final String variable, final boolean[] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            set(variable, value);
        }
    }

    @Override
    public void putBooleanMatrix(final String variable, final boolean[][] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            set(variable, value);
        }
    }

}
