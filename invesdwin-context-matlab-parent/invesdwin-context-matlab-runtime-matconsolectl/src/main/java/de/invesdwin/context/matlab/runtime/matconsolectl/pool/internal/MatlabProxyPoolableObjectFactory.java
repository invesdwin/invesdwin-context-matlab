package de.invesdwin.context.matlab.runtime.matconsolectl.pool.internal;

import java.io.OutputStreamWriter;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Named;

import org.springframework.beans.factory.FactoryBean;
import org.zeroturnaround.exec.stream.slf4j.Slf4jDebugOutputStream;
import org.zeroturnaround.exec.stream.slf4j.Slf4jWarnOutputStream;

import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.matlab.runtime.matconsolectl.MatConsoleCtlProperties;
import de.invesdwin.context.matlab.runtime.matconsolectl.MatConsoleCtlScriptTaskEngineMatlab;
import de.invesdwin.util.concurrent.pool.commons.ICommonsPoolableObjectFactory;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.MatlabProxyFactoryOptions;
import matlabcontrol.MatlabProxyFactoryOptions.Builder;

@ThreadSafe
@Named
public final class MatlabProxyPoolableObjectFactory
        implements ICommonsPoolableObjectFactory<MatlabProxy>, FactoryBean<MatlabProxyPoolableObjectFactory> {

    public static final MatlabProxyPoolableObjectFactory INSTANCE = new MatlabProxyPoolableObjectFactory();

    private MatlabProxyPoolableObjectFactory() {}

    @Override
    public MatlabProxy makeObject() {
        final Builder options = new MatlabProxyFactoryOptions.Builder().setHidden(true);
        if (MatConsoleCtlProperties.MATLAB_COMMAND != null) {
            options.setMatlabLocation(MatConsoleCtlProperties.MATLAB_COMMAND);
        }
        options.setOutputWriter(new OutputStreamWriter(new Slf4jDebugOutputStream(IScriptTaskRunnerMatlab.LOG)));
        options.setErrorWriter(new OutputStreamWriter(new Slf4jWarnOutputStream(IScriptTaskRunnerMatlab.LOG)));
        final MatlabProxyFactory factory = new MatlabProxyFactory(options.build());
        try {
            return factory.getProxy();
        } catch (final MatlabConnectionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void destroyObject(final MatlabProxy obj) {
        try {
            obj.exit();
        } catch (final MatlabInvocationException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean validateObject(final MatlabProxy obj) {
        return true;
    }

    @Override
    public void activateObject(final MatlabProxy obj) {}

    @Override
    public void passivateObject(final MatlabProxy obj) {
        final MatConsoleCtlScriptTaskEngineMatlab engine = new MatConsoleCtlScriptTaskEngineMatlab(obj);
        engine.eval(IScriptTaskRunnerMatlab.CLEANUP_SCRIPT);
        engine.close();
    }

    @Override
    public MatlabProxyPoolableObjectFactory getObject() {
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
