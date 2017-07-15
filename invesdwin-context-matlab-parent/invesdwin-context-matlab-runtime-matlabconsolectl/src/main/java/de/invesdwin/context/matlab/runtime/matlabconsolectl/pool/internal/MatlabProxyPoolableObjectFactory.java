package de.invesdwin.context.matlab.runtime.matlabconsolectl.pool.internal;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Named;

import org.springframework.beans.factory.FactoryBean;

import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.matlab.runtime.matlabconsolectl.MatlabConsoleCtlProperties;
import de.invesdwin.context.matlab.runtime.matlabconsolectl.MatlabConsoleCtlScriptTaskEngineMatlab;
import de.invesdwin.context.pool.IPoolableObjectFactory;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.MatlabProxyFactoryOptions;
import matlabcontrol.MatlabProxyFactoryOptions.Builder;

@ThreadSafe
@Named
public final class MatlabProxyPoolableObjectFactory
        implements IPoolableObjectFactory<MatlabProxy>, FactoryBean<MatlabProxyPoolableObjectFactory> {

    public static final MatlabProxyPoolableObjectFactory INSTANCE = new MatlabProxyPoolableObjectFactory();

    private MatlabProxyPoolableObjectFactory() {}

    @Override
    public MatlabProxy makeObject() {
        final Builder options = new MatlabProxyFactoryOptions.Builder().setHidden(true);
        if (MatlabConsoleCtlProperties.MATLAB_COMMAND != null) {
            options.setMatlabLocation(MatlabConsoleCtlProperties.MATLAB_COMMAND);
        }
        final MatlabProxyFactory factory = new MatlabProxyFactory(options.build());
        try {
            return factory.getProxy();
        } catch (final MatlabConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroyObject(final MatlabProxy obj) throws Exception {
        obj.exit();
    }

    @Override
    public boolean validateObject(final MatlabProxy obj) {
        return true;
    }

    @Override
    public void activateObject(final MatlabProxy obj) throws Exception {}

    @Override
    public void passivateObject(final MatlabProxy obj) throws Exception {
        final MatlabConsoleCtlScriptTaskEngineMatlab engine = new MatlabConsoleCtlScriptTaskEngineMatlab(obj);
        engine.eval(IScriptTaskRunnerMatlab.CLEANUP_SCRIPT);
        engine.close();
    }

    @Override
    public MatlabProxyPoolableObjectFactory getObject() throws Exception {
        return INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return MatlabProxyPoolableObjectFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
