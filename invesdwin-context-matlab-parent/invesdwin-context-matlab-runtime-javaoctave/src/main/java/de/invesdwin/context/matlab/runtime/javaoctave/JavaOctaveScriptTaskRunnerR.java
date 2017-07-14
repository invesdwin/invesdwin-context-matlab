package de.invesdwin.context.matlab.runtime.javaoctave;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import org.springframework.beans.factory.FactoryBean;

import com.github.rcaller.rstuff.RCaller;

import de.invesdwin.context.matlab.runtime.contract.AScriptTaskMatlab;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.matlab.runtime.javaoctave.pool.OctaveEngineObjectPool;
import de.invesdwin.util.error.Throwables;

@Immutable
@Named
public final class JavaOctaveScriptTaskRunnerR implements IScriptTaskRunnerMatlab, FactoryBean<JavaOctaveScriptTaskRunnerR> {

    public static final JavaOctaveScriptTaskRunnerR INSTANCE = new JavaOctaveScriptTaskRunnerR();

    public static final String INTERNAL_RESULT_VARIABLE = JavaOctaveScriptTaskRunnerR.class.getSimpleName() + "_result";

    /**
     * public for ServiceLoader support
     */
    public JavaOctaveScriptTaskRunnerR() {}

    @Override
    public <T> T run(final AScriptTaskMatlab<T> scriptTask) {
        //get session
        final RCaller rcaller;
        try {
            rcaller = OctaveEngineObjectPool.INSTANCE.borrowObject();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        try {
            //inputs
            rcaller.getRCode().clearOnline();
            final JavaOctaveScriptTaskEngineR engine = new JavaOctaveScriptTaskEngineR(rcaller);
            scriptTask.populateInputs(engine.getInputs());

            //execute
            scriptTask.executeScript(engine);

            //results
            final T result = scriptTask.extractResults(engine.getResults());
            engine.close();

            //return
            OctaveEngineObjectPool.INSTANCE.returnObject(rcaller);
            return result;
        } catch (final Throwable t) {
            try {
                OctaveEngineObjectPool.INSTANCE.invalidateObject(rcaller);
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
            throw Throwables.propagate(t);
        }
    }

    @Override
    public JavaOctaveScriptTaskRunnerR getObject() throws Exception {
        return INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return JavaOctaveScriptTaskRunnerR.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
