package de.invesdwin.context.matlab.ornsteinuhlenbeck;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Inject;

import org.junit.Test;

import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.matlab.runtime.javaoctave.JavaOctaveScriptTaskRunnerMatlab;
import de.invesdwin.context.test.ATest;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.math.decimal.Decimal;

@NotThreadSafe
public class OrnsteinUhlenbeckScriptTaskTest extends ATest {

    private static final int ITERATIONS = 10;
    @Inject
    private JavaOctaveScriptTaskRunnerMatlab rcallerScriptTaskRunner;

    @Test
    public void testRCaller() {
        for (int i = 0; i < ITERATIONS; i++) {
            run(rcallerScriptTaskRunner);
            log.info("------------------------");
        }
    }

    private void run(final IScriptTaskRunnerMatlab runner) {
        final List<List<Double>> tradesPerStrategy = new ArrayList<>();
        //        0.5,-0.3,0.4,-0.2
        tradesPerStrategy.add(Arrays.asList(0.5, -0.3, 0.4, -0.2));
        //        0.1,-0.15,0.4,-0.1
        tradesPerStrategy.add(Arrays.asList(0.1, -0.15, 0.4, -0.1));
        final List<Double> optimalFsRaw = new OrnsteinUhlenbeckScriptTask(tradesPerStrategy).run(runner);
        final List<Decimal> optimalFs = new ArrayList<Decimal>();
        for (final Double optimalFStr : optimalFsRaw) {
            optimalFs.add(new Decimal(optimalFStr).round(3));
        }
        Assertions.assertThat(optimalFs).isEqualTo(Arrays.asList(new Decimal("0.052"), new Decimal("0.213")));
    }

    @Test
    public void testNegative() {
        final List<List<Double>> tradesPerStrategy = new ArrayList<>();
        //        0.5,-0.3,0.4,-0.2
        tradesPerStrategy.add(Arrays.asList(-0.5, -0.3, -0.4, -0.2));
        //        0.1,-0.15,0.4,-0.1
        tradesPerStrategy.add(Arrays.asList(-0.1, -0.15, -0.4, -0.1));
        final List<Double> optimalFsRaw = new OrnsteinUhlenbeckScriptTask(tradesPerStrategy)
                .run(rcallerScriptTaskRunner);
        final List<Decimal> optimalFs = new ArrayList<Decimal>();
        for (final Double optimalFStr : optimalFsRaw) {
            optimalFs.add(new Decimal(optimalFStr).round(3));
        }
        Assertions.assertThat(optimalFs).isEqualTo(Arrays.asList(Decimal.ZERO, Decimal.ZERO));
    }

    @Test
    public void testPositive() {
        final List<List<Double>> tradesPerStrategy = new ArrayList<>();
        //        0.5,-0.3,0.4,-0.2
        tradesPerStrategy.add(Arrays.asList(0.5, 0.3, 0.4, 0.2));
        //        0.1,-0.15,0.4,-0.1
        tradesPerStrategy.add(Arrays.asList(0.1, 0.15, 0.4, 0.1));
        try {
            new OrnsteinUhlenbeckScriptTask(tradesPerStrategy).run(rcallerScriptTaskRunner);
            Assertions.failExceptionExpected();
        } catch (final Throwable t) {
            Assertions.assertThat(t.getMessage())
                    .contains("all 'events' columns must have at least one negative trade");
        }
    }

    @Test
    public void testEmptyTrades() {
        final List<List<Double>> tradesPerStrategy = new ArrayList<>();
        //        0.5,-0.3,0.4,-0.2
        tradesPerStrategy.add(Arrays.asList());
        //        0.1,-0.15,0.4,-0.1
        tradesPerStrategy.add(Arrays.asList());
        try {
            new OrnsteinUhlenbeckScriptTask(tradesPerStrategy).run(rcallerScriptTaskRunner);
            Assertions.failExceptionExpected();
        } catch (final Throwable t) {
            Assertions.assertThat(t.getMessage())
                    .contains("all 'events' columns must have at least one negative trade");
        }
    }

    @Test
    public void testEmptyStrategies() {
        final List<List<Double>> tradesPerStrategy = new ArrayList<>();
        try {
            new OrnsteinUhlenbeckScriptTask(tradesPerStrategy).run(rcallerScriptTaskRunner);
            Assertions.failExceptionExpected();
        } catch (final Throwable t) {
            Assertions.assertThat(t.getMessage()).contains("No trades!");
        }
    }

}
