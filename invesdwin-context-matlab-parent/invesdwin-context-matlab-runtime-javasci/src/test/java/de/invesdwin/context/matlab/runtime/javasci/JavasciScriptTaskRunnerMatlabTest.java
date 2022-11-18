package de.invesdwin.context.matlab.runtime.javasci;

import javax.annotation.concurrent.NotThreadSafe;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import de.invesdwin.context.matlab.runtime.javasci.test.InputsAndResultsTests;
import de.invesdwin.context.test.ATest;

@NotThreadSafe
public class JavasciScriptTaskRunnerMatlabTest extends ATest {

    @Inject
    private JavasciScriptTaskRunnerMatlab runner;

    @Test
    public void test() {
        new InputsAndResultsTests(runner).test();
    }

    @Test
    public void testParallel() {
        new InputsAndResultsTests(runner).testParallel();
    }

}
