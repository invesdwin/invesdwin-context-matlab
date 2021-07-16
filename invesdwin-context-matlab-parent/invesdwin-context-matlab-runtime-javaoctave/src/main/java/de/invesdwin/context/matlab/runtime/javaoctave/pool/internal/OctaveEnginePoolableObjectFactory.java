package de.invesdwin.context.matlab.runtime.javaoctave.pool.internal;

import java.io.File;
import java.io.OutputStreamWriter;

import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Named;

import org.springframework.beans.factory.FactoryBean;
import org.zeroturnaround.exec.stream.slf4j.Slf4jDebugOutputStream;
import org.zeroturnaround.exec.stream.slf4j.Slf4jWarnOutputStream;

import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.context.matlab.runtime.javaoctave.JavaOctaveProperties;
import de.invesdwin.context.matlab.runtime.javaoctave.JavaOctaveScriptTaskEngineMatlab;
import de.invesdwin.util.concurrent.pool.IPoolableObjectFactory;
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
        factory.setErrorWriter(new OutputStreamWriter(new Slf4jWarnOutputStream(IScriptTaskRunnerMatlab.LOG)));
        if (JavaOctaveProperties.OCTAVE_COMMAND != null) {
            factory.setOctaveProgram(new File(JavaOctaveProperties.OCTAVE_COMMAND));
        }
        final OctaveEngine scriptEngine = factory.getScriptEngine();
        scriptEngine.setWriter(new OutputStreamWriter(new Slf4jDebugOutputStream(IScriptTaskRunnerMatlab.LOG)));
        return scriptEngine;
    }

    @Override
    public void destroyObject(final OctaveEngine obj) {
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
    public void activateObject(final OctaveEngine obj) {}

    @Override
    public void passivateObject(final OctaveEngine obj) {
        final JavaOctaveScriptTaskEngineMatlab engine = new JavaOctaveScriptTaskEngineMatlab(obj);
        engine.eval(IScriptTaskRunnerMatlab.CLEANUP_SCRIPT);
        engine.close();
    }

    @Override
    public OctaveEnginePoolableObjectFactory getObject() {
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
