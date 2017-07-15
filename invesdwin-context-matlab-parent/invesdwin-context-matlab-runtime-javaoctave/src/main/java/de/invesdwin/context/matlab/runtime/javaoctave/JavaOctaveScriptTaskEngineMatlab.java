package de.invesdwin.context.matlab.runtime.javaoctave;

import javax.annotation.concurrent.NotThreadSafe;

import de.invesdwin.context.integration.script.IScriptTaskEngine;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import dk.ange.octave.OctaveEngine;

@NotThreadSafe
public class JavaOctaveScriptTaskEngineMatlab implements IScriptTaskEngine {

    private OctaveEngine octaveEngine;
    private final JavaOctaveScriptTaskInputsMatlab inputs;
    private final JavaOctaveScriptTaskResultsMatlab results;
    private String expressionEnding;

    public JavaOctaveScriptTaskEngineMatlab(final OctaveEngine octaveEngine) {
        this.octaveEngine = octaveEngine;
        this.inputs = new JavaOctaveScriptTaskInputsMatlab(this);
        this.results = new JavaOctaveScriptTaskResultsMatlab(this);
        if (IScriptTaskRunnerMatlab.LOG.isDebugEnabled()) {
            expressionEnding = ";";
        } else {
            expressionEnding = "";
        }
    }

    @Override
    public void eval(final String expression) {
        octaveEngine.eval(expression + expressionEnding);
    }

    @Override
    public JavaOctaveScriptTaskInputsMatlab getInputs() {
        return inputs;
    }

    @Override
    public JavaOctaveScriptTaskResultsMatlab getResults() {
        return results;
    }

    @Override
    public void close() {
        octaveEngine = null;
    }

    @Override
    public OctaveEngine unwrap() {
        return octaveEngine;
    }

}
