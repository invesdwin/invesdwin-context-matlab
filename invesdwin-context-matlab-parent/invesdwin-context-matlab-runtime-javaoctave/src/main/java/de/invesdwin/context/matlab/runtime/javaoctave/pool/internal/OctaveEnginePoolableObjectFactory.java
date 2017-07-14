package de.invesdwin.context.matlab.runtime.javaoctave.pool.internal;

import java.io.OutputStreamWriter;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Named;

import org.springframework.beans.factory.FactoryBean;
import org.zeroturnaround.exec.stream.slf4j.Slf4jDebugOutputStream;
import org.zeroturnaround.exec.stream.slf4j.Slf4jWarnOutputStream;

import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.pool.IPoolableObjectFactory;
import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;

@ThreadSafe
@Named
public final class OctaveEnginePoolableObjectFactory
        implements IPoolableObjectFactory<OctaveEngine>, FactoryBean<OctaveEnginePoolableObjectFactory> {

    public static final OctaveEnginePoolableObjectFactory INSTANCE = new OctaveEnginePoolableObjectFactory();

    private OctaveEnginePoolableObjectFactory() {}

    @Override
    public OctaveEngine makeObject() {
        final OctaveEngineFactory factory = new OctaveEngineFactory();
        final OctaveEngine scriptEngine = factory.getScriptEngine();
        scriptEngine.setWriter(new OutputStreamWriter(new Slf4jDebugOutputStream(IScriptTaskRunnerMatlab.LOG)));
        scriptEngine.setErrorWriter(new OutputStreamWriter(new Slf4jWarnOutputStream(IScriptTaskRunnerMatlab.LOG)));
        return scriptEngine;
    }

    @Override
    public void destroyObject(final OctaveEngine obj) throws Exception {
        try {
            obj.close();
        } catch (final Throwable t) {
            //ignore
        }
    }

    @Override
    public boolean validateObject(final OctaveEngine obj) {
        return true;
    }

    @Override
    public void activateObject(final OctaveEngine obj) throws Exception {}

    @Override
    public void passivateObject(final OctaveEngine obj) throws Exception {
        obj.eval(IScriptTaskRunnerMatlab.CLEANUP_SCRIPT);
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
