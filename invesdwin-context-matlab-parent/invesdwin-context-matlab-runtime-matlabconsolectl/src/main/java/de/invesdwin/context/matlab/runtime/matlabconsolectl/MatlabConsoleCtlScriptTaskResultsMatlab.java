package de.invesdwin.context.matlab.runtime.matlabconsolectl;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.matlab.runtime.contract.IScriptTaskResultsMatlab;
import de.invesdwin.util.lang.Strings;
import de.invesdwin.util.math.Booleans;
import de.invesdwin.util.math.Doubles;
import de.invesdwin.util.math.Integers;
import matlabcontrol.MatlabInvocationException;

@NotThreadSafe
public class MatlabConsoleCtlScriptTaskResultsMatlab implements IScriptTaskResultsMatlab {

    private final MatlabConsoleCtlScriptTaskEngineMatlab engine;

    public MatlabConsoleCtlScriptTaskResultsMatlab(final MatlabConsoleCtlScriptTaskEngineMatlab engine) {
        this.engine = engine;
    }

    @Override
    public MatlabConsoleCtlScriptTaskEngineMatlab getEngine() {
        return engine;
    }

    @Override
    public String getString(final String variable) {
        if (isNull(variable)) {
            return null;
        } else {
            final Object obj = get(variable);
            return Strings.checkedCast(obj);
        }
    }

    private Object get(final String variable) {
        try {
            return engine.unwrap().returningEval(variable, 1)[0];
        } catch (final MatlabInvocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String[] getStringVector(final String variable) {
        if (isNull(variable)) {
            return null;
        } else if (isEmpty(variable)) {
            return new String[0];
        } else {
            final Object obj = get(variable);
            return Strings.checkedCastVector(obj);
        }
    }

    @Override
    public String[][] getStringMatrix(final String variable) {
        if (isNull(variable)) {
            return null;
        } else if (isEmpty(variable)) {
            final int rows = getInteger("size(" + variable + ",1)");
            final String[][] matrix = new String[rows][];
            for (int i = 0; i < rows; i++) {
                matrix[i] = new String[0];
            }
            return matrix;
        } else {
            final Object obj = get(variable);
            return Strings.checkedCastMatrix(obj);
        }
    }

    @Override
    public double getDouble(final String variable) {
        final Object obj = get(variable);
        return Doubles.checkedCast(obj);
    }

    @Override
    public double[] getDoubleVector(final String variable) {
        if (isNull(variable)) {
            return null;
        } else if (isEmpty(variable)) {
            return new double[0];
        } else {
            final Object obj = get(variable);
            return Doubles.checkedCastVector(obj);
        }
    }

    @Override
    public double[][] getDoubleMatrix(final String variable) {
        if (isNull(variable)) {
            return null;
        } else if (isEmpty(variable)) {
            final int rows = getInteger("size(" + variable + ",1)");
            final double[][] matrix = new double[rows][];
            for (int i = 0; i < rows; i++) {
                matrix[i] = new double[0];
            }
            return matrix;
        } else {
            final Object obj = get(variable);
            return Doubles.checkedCastMatrix(obj);
        }
    }

    @Override
    public int getInteger(final String variable) {
        final Object obj = get(variable);
        return Integers.checkedCast(obj);
    }

    @Override
    public int[] getIntegerVector(final String variable) {
        if (isNull(variable)) {
            return null;
        } else if (isEmpty(variable)) {
            return new int[0];
        } else {
            final Object obj = get(variable);
            return Integers.checkedCastVector(obj);
        }
    }

    @Override
    public int[][] getIntegerMatrix(final String variable) {
        if (isNull(variable)) {
            return null;
        } else if (isEmpty(variable)) {
            final int rows = getInteger("size(" + variable + ",1)");
            final int[][] matrix = new int[rows][];
            for (int i = 0; i < rows; i++) {
                matrix[i] = new int[0];
            }
            return matrix;
        } else {
            final Object obj = get(variable);
            return Integers.checkedCastMatrix(obj);
        }
    }

    @Override
    public boolean getBoolean(final String variable) {
        final Object obj = get(variable);
        return Booleans.checkedCast(obj);
    }

    @Override
    public boolean[] getBooleanVector(final String variable) {
        if (isNull(variable)) {
            return null;
        } else if (isEmpty(variable)) {
            return new boolean[0];
        } else {
            final Object obj = get(variable);
            return Booleans.checkedCastVector(obj);
        }
    }

    @Override
    public boolean[][] getBooleanMatrix(final String variable) {
        if (isNull(variable)) {
            return null;
        } else if (isEmpty(variable)) {
            final int rows = getInteger("size(" + variable + ",1)");
            final boolean[][] matrix = new boolean[rows][];
            for (int i = 0; i < rows; i++) {
                matrix[i] = new boolean[0];
            }
            return matrix;
        } else {
            final Object obj = get(variable);
            return Booleans.checkedCastMatrix(obj);
        }
    }

}