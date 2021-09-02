package de.invesdwin.context.matlab.runtime.matconsolectl;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import org.springframework.beans.factory.FactoryBean;

import de.invesdwin.context.matlab.runtime.contract.AScriptTaskMatlab;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.matlab.runtime.matconsolectl.pool.MatlabProxyObjectPool;
import de.invesdwin.util.error.Throwables;
import matlabcontrol.MatlabProxy;

@Immutable
@Named
public final class MatConsoleCtlScriptTaskRunnerMatlab
        implements IScriptTaskRunnerMatlab, FactoryBean<MatConsoleCtlScriptTaskRunnerMatlab> {

    public static final MatConsoleCtlScriptTaskRunnerMatlab INSTANCE = new MatConsoleCtlScriptTaskRunnerMatlab();

    public static final String INTERNAL_RESULT_VARIABLE = MatConsoleCtlScriptTaskRunnerMatlab.class.getSimpleName()
            + "_result";

    /**
     * public for ServiceLoader support
     */
    public MatConsoleCtlScriptTaskRunnerMatlab() {
    }

    @Override
    public <T> T run(final AScriptTaskMatlab<T> scriptTask) {
        //get session
        final MatlabProxy matlabProxy = MatlabProxyObjectPool.INSTANCE.borrowObject();
        try {
            //inputs
            final MatConsoleCtlScriptTaskEngineMatlab engine = new MatConsoleCtlScriptTaskEngineMatlab(matlabProxy);
            scriptTask.populateInputs(engine.getInputs());

            //execute
            scriptTask.executeScript(engine);

            //results
            final T result = scriptTask.extractResults(engine.getResults());
            engine.close();

            //return
            MatlabProxyObjectPool.INSTANCE.returnObject(matlabProxy);
            return result;
        } catch (final Throwable t) {
            MatlabProxyObjectPool.INSTANCE.destroyObject(matlabProxy);
            throw Throwables.propagate(t);
        }
    }

    @Override
    public MatConsoleCtlScriptTaskRunnerMatlab getObject() throws Exception {
        return INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return MatConsoleCtlScriptTaskRunnerMatlab.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
