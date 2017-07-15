package de.invesdwin.context.matlab.runtime.matlabconsolectl;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.matlab.runtime.contract.IScriptTaskInputsMatlab;
import de.invesdwin.util.math.Doubles;
import dk.ange.octave.type.Octave;
import dk.ange.octave.type.OctaveBoolean;
import dk.ange.octave.type.OctaveCell;
import dk.ange.octave.type.OctaveDouble;
import dk.ange.octave.type.OctaveString;

@NotThreadSafe
public class MatlabConsoleCtlScriptTaskInputsMatlab implements IScriptTaskInputsMatlab {

    private final MatlabConsoleCtlScriptTaskEngineMatlab engine;

    public MatlabConsoleCtlScriptTaskInputsMatlab(final MatlabConsoleCtlScriptTaskEngineMatlab engine) {
        this.engine = engine;
    }

    @Override
    public MatlabConsoleCtlScriptTaskEngineMatlab getEngine() {
        return engine;
    }

    @Override
    public void putString(final String variable, final String value) {
        if (value == null) {
            engine.unwrap().put(variable, new OctaveString(""));
        } else {
            engine.unwrap().put(variable, new OctaveString(value));
        }
    }

    @Override
    public void putStringVector(final String variable, final String[] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            final OctaveCell vector = new OctaveCell(value.length, 1);
            for (int i = 0; i < value.length; i++) {
                String str = value[i];
                if (str == null) {
                    str = "";
                }
                vector.set(new OctaveString(str), i + 1, 1);
            }
            engine.unwrap().put(variable, vector);
        }
    }

    @Override
    public void putStringMatrix(final String variable, final String[][] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            final int rows = value.length;
            final int cols = value[0].length;
            final OctaveCell matrix = new OctaveCell(rows, cols);
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    String str = value[row][col];
                    if (str == null) {
                        str = "";
                    }
                    matrix.set(new OctaveString(str), row + 1, col + 1);
                }
            }
            engine.unwrap().put(variable, matrix);
        }
    }

    @Override
    public void putDouble(final String variable, final double value) {
        engine.unwrap().put(variable, Octave.scalar(value));
    }

    @Override
    public void putDoubleVector(final String variable, final double[] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            final OctaveDouble vector = new OctaveDouble(value.length, 1);
            for (int i = 0; i < value.length; i++) {
                vector.set(value[i], i + 1, 1);
            }
            engine.unwrap().put(variable, vector);
        }
    }

    @Override
    public void putDoubleMatrix(final String variable, final double[][] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            final int rows = value.length;
            final int cols = value[0].length;
            final OctaveDouble matrix = new OctaveDouble(rows, cols);
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    matrix.set(value[row][col], row + 1, col + 1);
                }
            }
            engine.unwrap().put(variable, matrix);
        }
    }

    @Override
    public void putInteger(final String variable, final int value) {
        //even though there is the OctaveInt type, it cannot be written...
        putDouble(variable, value);
        putExpression(variable, "int32(" + variable + ")");
    }

    @Override
    public void putIntegerVector(final String variable, final int[] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            //even though there is the OctaveInt type, it cannot be written...
            putDoubleVector(variable, Doubles.checkedCastVector(value));
            putExpression(variable, "int32(" + variable + ")");
        }
    }

    @Override
    public void putIntegerMatrix(final String variable, final int[][] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            //even though there is the OctaveInt type, it cannot be written...
            putDoubleMatrix(variable, Doubles.checkedCastMatrix(value));
            putExpression(variable, "int32(" + variable + ")");
        }
    }

    @Override
    public void putBoolean(final String variable, final boolean value) {
        final OctaveBoolean oi = new OctaveBoolean(1, 1);
        oi.set(value, 1, 1);
        engine.unwrap().put(variable, oi);
    }

    @Override
    public void putBooleanVector(final String variable, final boolean[] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            final OctaveBoolean vector = new OctaveBoolean(value.length, 1);
            for (int i = 0; i < value.length; i++) {
                vector.set(value[i], i + 1, 1);
            }
            engine.unwrap().put(variable, vector);
        }
    }

    @Override
    public void putBooleanMatrix(final String variable, final boolean[][] value) {
        if (value == null) {
            putNull(variable);
        } else if (value.length == 0) {
            putEmpty(variable);
        } else {
            final int rows = value.length;
            final int cols = value[0].length;
            final OctaveBoolean matrix = new OctaveBoolean(rows, cols);
            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    matrix.set(value[row][col], row + 1, col + 1);
                }
            }
            engine.unwrap().put(variable, matrix);
        }
    }

}
