package de.invesdwin.context.matlab.runtime.javasci;

import java.util.concurrent.locks.ReentrantLock;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.Immutable;
import javax.inject.Named;

import org.scilab.modules.javasci.JavasciException;
import org.scilab.modules.javasci.JavasciException.InitializationException;
import org.scilab.modules.javasci.Scilab;
import org.springframework.beans.factory.FactoryBean;

import de.invesdwin.context.matlab.runtime.contract.AScriptTaskMatlab;
import de.invesdwin.context.matlab.runtime.contract.IScriptTaskRunnerMatlab;
import de.invesdwin.instrument.DynamicInstrumentationReflections;
import de.invesdwin.util.error.Throwables;

@Immutable
@Named
public final class JavasciScriptTaskRunnerMatlab
        implements IScriptTaskRunnerMatlab, FactoryBean<JavasciScriptTaskRunnerMatlab> {

    public static final String INTERNAL_RESULT_VARIABLE = JavasciScriptTaskRunnerMatlab.class.getSimpleName()
            + "_result";

    public static final JavasciScriptTaskRunnerMatlab INSTANCE = new JavasciScriptTaskRunnerMatlab();

    @GuardedBy("SCILAB_LOCK")
    private static final Scilab SCILAB;
    private static final ReentrantLock SCILAB_LOCK;

    static {
        DynamicInstrumentationReflections.addPathToJavaLibraryPath(JavasciProperties.JAVASCI_LIBRARY_PATH);
        try {
            SCILAB = new Scilab();
            SCILAB.open();
        } catch (final InitializationException e) {
            throw new RuntimeException(e);
        } catch (final JavasciException e) {
            throw new RuntimeException(e);
        }
        SCILAB_LOCK = new ReentrantLock();
    }

    /**
     * public for ServiceLoader support
     */
    public JavasciScriptTaskRunnerMatlab() {}

    @Override
    public <T> T run(final AScriptTaskMatlab<T> scriptTask) {
        //get session
        SCILAB_LOCK.lock();
        try {
            //inputs
            final JavasciScriptTaskEngineMatlab engine = new JavasciScriptTaskEngineMatlab(SCILAB);
            scriptTask.populateInputs(engine.getInputs());

            //execute
            scriptTask.executeScript(engine);

            //results
            final T result = scriptTask.extractResults(engine.getResults());
            engine.close();

            //return
            SCILAB_LOCK.unlock();
            return result;
        } catch (final Throwable t) {
            SCILAB_LOCK.unlock();
            throw Throwables.propagate(t);
        }
    }

    @Override
    public JavasciScriptTaskRunnerMatlab getObject() throws Exception {
        return INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return JavasciScriptTaskRunnerMatlab.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
