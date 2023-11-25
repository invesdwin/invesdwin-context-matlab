package de.invesdwin.context.matlab.runtime.javasci.callback.socket;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.matlab.runtime.contract.callback.ScriptTaskReturnsMatlabToExpression;
import de.invesdwin.util.math.Doubles;

@NotThreadSafe
public class JavasciScriptTaskReturnsMatlabToExpression extends ScriptTaskReturnsMatlabToExpression {

    @Override
    public void returnFloat(final float value) {
        returnDouble(value);
    }

    @Override
    public void returnFloatVector(final float[] value) {
        returnDoubleVector(Doubles.checkedCastVector(value));
    }

    @Override
    public void returnFloatMatrix(final float[][] value) {
        returnDoubleMatrix(Doubles.checkedCastMatrix(value));
    }

}
