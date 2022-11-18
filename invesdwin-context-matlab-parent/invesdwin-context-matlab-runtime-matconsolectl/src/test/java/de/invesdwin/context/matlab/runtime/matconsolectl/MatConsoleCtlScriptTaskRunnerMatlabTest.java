package de.invesdwin.context.matlab.runtime.matconsolectl;

import javax.annotation.concurrent.NotThreadSafe;
import jakarta.inject.Inject;

import org.junit.jupiter.api.Test;

import de.invesdwin.context.matlab.runtime.contract.InputsAndResultsTests;
import de.invesdwin.context.test.ATest;

@NotThreadSafe
public class MatConsoleCtlScriptTaskRunnerMatlabTest extends ATest {

    @Inject
    private MatConsoleCtlScriptTaskRunnerMatlab runner;

    @Test
    public void test() {
        new InputsAndResultsTests(runner).test();
    }

    @Test
    public void testParallel() {
        new InputsAndResultsTests(runner).testParallel();
    }

}
