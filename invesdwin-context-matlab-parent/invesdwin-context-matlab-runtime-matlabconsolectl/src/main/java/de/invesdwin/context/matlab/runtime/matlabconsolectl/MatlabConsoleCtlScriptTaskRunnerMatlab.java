package de.invesdwin.context.matlab.runtime.matlabconsolectl;

import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import org.springframework.beans.factory.FactoryBean;

import de.invesdwin.context.matlab.runtime.contract.AScriptTaskMatlab;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.matlab.runtime.matlabconsolectl.pool.MatlabProxyObjectPool;
import de.invesdwin.util.error.Throwables;
import matlabcontrol.MatlabProxy;

@Immutable
@Named
public final class MatlabConsoleCtlScriptTaskRunnerMatlab
        implements IScriptTaskRunnerMatlab, FactoryBean<MatlabConsoleCtlScriptTaskRunnerMatlab> {

    public static final MatlabConsoleCtlScriptTaskRunnerMatlab INSTANCE = new MatlabConsoleCtlScriptTaskRunnerMatlab();

    /**
     * public for ServiceLoader support
     */
    public MatlabConsoleCtlScriptTaskRunnerMatlab() {}

    @Override
    public <T> T run(final AScriptTaskMatlab<T> scriptTask) {
        //get session
        final MatlabProxy matlabProxy;
        try {
            matlabProxy = MatlabProxyObjectPool.INSTANCE.borrowObject();
        } catch (final Exception e) {
            throw new RuntimeException(e);
        }
        try {
            //inputs
            final MatlabConsoleCtlScriptTaskEngineMatlab engine = new MatlabConsoleCtlScriptTaskEngineMatlab(
                    matlabProxy);
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
            try {
                MatlabProxyObjectPool.INSTANCE.invalidateObject(matlabProxy);
            } catch (final Exception e) {
                throw new RuntimeException(e);
            }
            throw Throwables.propagate(t);
        }
    }

    @Override
    public MatlabConsoleCtlScriptTaskRunnerMatlab getObject() throws Exception {
        return INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return MatlabConsoleCtlScriptTaskRunnerMatlab.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
