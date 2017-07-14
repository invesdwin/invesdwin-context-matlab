package de.invesdwin.context.matlab.runtime.javaoctave.pool.internal;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Named;

import org.springframework.beans.factory.FactoryBean;

import com.github.rcaller.rstuff.RCaller;

import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.matlab.runtime.javaoctave.JavaOctaveScriptTaskRunnerR;
import de.invesdwin.context.pool.IPoolableObjectFactory;

@ThreadSafe
@Named
public final class OctaveEnginePoolableObjectFactory
        implements IPoolableObjectFactory<RCaller>, FactoryBean<OctaveEnginePoolableObjectFactory> {

    public static final OctaveEnginePoolableObjectFactory INSTANCE = new OctaveEnginePoolableObjectFactory();

    private OctaveEnginePoolableObjectFactory() {}

    @Override
    public RCaller makeObject() {
        return new ModifiedRCaller();
    }

    @Override
    public void destroyObject(final RCaller obj) throws Exception {
        obj.StopRCallerOnline();
    }

    @Override
    public boolean validateObject(final RCaller obj) {
        return true;
    }

    @Override
    public void activateObject(final RCaller obj) throws Exception {}

    @Override
    public void passivateObject(final RCaller obj) throws Exception {
        obj.getRCode().clear();
        obj.getRCode().getCode().insert(0, IScriptTaskRunnerMatlab.CLEANUP_SCRIPT + "\n");
        obj.getRCode().addRCode(JavaOctaveScriptTaskRunnerR.INTERNAL_RESULT_VARIABLE + " <- c()");
        obj.runAndReturnResultOnline(JavaOctaveScriptTaskRunnerR.INTERNAL_RESULT_VARIABLE);
        obj.deleteTempFiles();
    }

    @Override
    public OctaveEnginePoolableObjectFactory getObject() throws Exception {
        return INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return OctaveEnginePoolableObjectFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
