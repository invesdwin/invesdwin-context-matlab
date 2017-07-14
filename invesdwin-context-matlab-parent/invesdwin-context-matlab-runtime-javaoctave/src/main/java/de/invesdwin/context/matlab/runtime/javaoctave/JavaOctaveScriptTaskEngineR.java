package de.invesdwin.context.matlab.runtime.javaoctave;

import javax.annotation.concurrent.NotThreadSafe;

import com.github.rcaller.rstuff.RCaller;

import de.invesdwin.context.integration.script.IScriptTaskEngine;

@NotThreadSafe
public class JavaOctaveScriptTaskEngineR implements IScriptTaskEngine {

    private RCaller rcaller;
    private final JavaOctaveScriptTaskInputsR inputs;
    private final JavaOctaveScriptTaskResultsR results;

    public JavaOctaveScriptTaskEngineR(final RCaller rcaller) {
        this.rcaller = rcaller;
        this.inputs = new JavaOctaveScriptTaskInputsR(this);
        this.results = new JavaOctaveScriptTaskResultsR(this);
    }

    @Override
    public void eval(final String expression) {
        rcaller.getRCode().addRCode(expression);
        rcaller.getRCode().addRCode(JavaOctaveScriptTaskRunnerR.INTERNAL_RESULT_VARIABLE + " <- c()");
        rcaller.runAndReturnResultOnline(JavaOctaveScriptTaskRunnerR.INTERNAL_RESULT_VARIABLE);
    }

    @Override
    public JavaOctaveScriptTaskInputsR getInputs() {
        return inputs;
    }

    @Override
    public JavaOctaveScriptTaskResultsR getResults() {
        return results;
    }

    @Override
    public void close() {
        rcaller = null;
    }

    @Override
    public RCaller unwrap() {
        return rcaller;
    }

}
