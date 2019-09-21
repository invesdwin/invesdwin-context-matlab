package de.invesdwin.context.matlab.runtime.javaoctave;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import org.springframework.beans.factory.FactoryBean;

import de.invesdwin.context.matlab.runtime.contract.AScriptTaskMatlab;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.matlab.runtime.javaoctave.pool.OctaveEngineObjectPool;
import de.invesdwin.util.error.Throwables;
import dk.ange.octave.OctaveEngine;

@Immutable
@Named
public final class JavaOctaveScriptTaskRunnerMatlab
        implements IScriptTaskRunnerMatlab, FactoryBean<JavaOctaveScriptTaskRunnerMatlab> {

    public static final JavaOctaveScriptTaskRunnerMatlab INSTANCE = new JavaOctaveScriptTaskRunnerMatlab();

    public static final String INTERNAL_RESULT_VARIABLE = JavaOctaveScriptTaskRunnerMatlab.class.getSimpleName()
            + "_result";

    /**
     * public for ServiceLoader support
     */
    public JavaOctaveScriptTaskRunnerMatlab() {}

    @Override
    public <T> T run(final AScriptTaskMatlab<T> scriptTask) {
        //get session
        final OctaveEngine octaveEngine = OctaveEngineObjectPool.INSTANCE.borrowObject();
        try {
            //inputs
            final JavaOctaveScriptTaskEngineMatlab engine = new JavaOctaveScriptTaskEngineMatlab(octaveEngine);
            scriptTask.populateInputs(engine.getInputs());

            //execute
            scriptTask.executeScript(engine);

            //results
            final T result = scriptTask.extractResults(engine.getResults());
            engine.close();

            //return
            OctaveEngineObjectPool.INSTANCE.returnObject(octaveEngine);
            return result;
        } catch (final Throwable t) {
            OctaveEngineObjectPool.INSTANCE.invalidateObject(octaveEngine);
            throw Throwables.propagate(t);
        }
    }

    @Override
    public JavaOctaveScriptTaskRunnerMatlab getObject() throws Exception {
        return INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return JavaOctaveScriptTaskRunnerMatlab.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
