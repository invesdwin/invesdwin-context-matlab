package de.invesdwin.context.matlab.runtime.javasci;

import javax.annotation.concurrent.NotThreadSafe;

import org.scilab.modules.javasci.JavasciException.ScilabErrorException;
import org.scilab.modules.javasci.Scilab;

import de.invesdwin.context.integration.script.IScriptTaskEngine;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;

@NotThreadSafe
public class JavasciScriptTaskEngineMatlab implements IScriptTaskEngine {

    private Scilab scilab;
    private final JavasciScriptTaskInputsMatlab inputs;
    private final JavasciScriptTaskResultsMatlab results;
    private String expressionEnding;

    public JavasciScriptTaskEngineMatlab(final Scilab octaveEngine) {
        this.scilab = octaveEngine;
        this.inputs = new JavasciScriptTaskInputsMatlab(this);
        this.results = new JavasciScriptTaskResultsMatlab(this);
        if (IScriptTaskRunnerMatlab.LOG.isDebugEnabled()) {
            expressionEnding = ";";
        } else {
            expressionEnding = "";
        }
    }

    @Override
    public void eval(final String expression) {
        try {
            scilab.execException(expression + expressionEnding);
        } catch (final ScilabErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public JavasciScriptTaskInputsMatlab getInputs() {
        return inputs;
    }

    @Override
    public JavasciScriptTaskResultsMatlab getResults() {
        return results;
    }

    @Override
    public void close() {
        scilab = null;
    }

    @Override
    public Scilab unwrap() {
        return scilab;
    }

}
