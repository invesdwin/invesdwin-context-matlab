package de.invesdwin.context.matlab.runtime.jascib;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.matlab.runtime.contract.AScriptTaskInputsMatlabToExpression;

@NotThreadSafe
public class JascibScriptTaskInputsMatlab extends AScriptTaskInputsMatlabToExpression {

    private final JascibScriptTaskEngineMatlab engine;

    public JascibScriptTaskInputsMatlab(final JascibScriptTaskEngineMatlab engine) {
        this.engine = engine;
    }

    @Override
    public JascibScriptTaskEngineMatlab getEngine() {
        return engine;
    }

}
