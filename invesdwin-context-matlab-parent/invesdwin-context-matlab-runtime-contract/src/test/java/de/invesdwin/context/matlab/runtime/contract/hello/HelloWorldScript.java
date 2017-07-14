package de.invesdwin.context.matlab.runtime.contract.hello;

import javax.annotation.concurrent.NotThreadSafe;

import org.springframework.core.io.ClassPathResource;

import de.invesdwin.context.integration.script.IScriptTaskEngine;
import de.invesdwin.context.integration.script.IScriptTaskInputs;
import de.invesdwin.context.integration.script.IScriptTaskResults;
import de.invesdwin.context.matlab.runtime.contract.AScriptTaskMatlab;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.test.ATest;
import de.invesdwin.util.assertions.Assertions;

@NotThreadSafe
public class HelloWorldScript extends ATest {

    private final IScriptTaskRunnerMatlab runner;

    public HelloWorldScript(final IScriptTaskRunnerMatlab runner) {
        this.runner = runner;
    }

    public void testHelloWorld() {
        final AScriptTaskMatlab<String> script = new AScriptTaskMatlab<String>() {

            @Override
            public void populateInputs(final IScriptTaskInputs inputs) {
                inputs.putString("hello", "World");
            }

            @Override
            public void executeScript(final IScriptTaskEngine engine) {
                //execute this script inline:
                //                engine.eval("world = strcat({'Hello '}, hello, '!')");
                //or run it from a file:
                engine.eval(new ClassPathResource(HelloWorldScript.class.getSimpleName() + ".m", getClass()));
            }

            @Override
            public String extractResults(final IScriptTaskResults results) {
                return results.getString("world");
            }
        };
        final String result = script.run(runner);
        Assertions.assertThat(result).isEqualTo("Hello World!");
    }

}
