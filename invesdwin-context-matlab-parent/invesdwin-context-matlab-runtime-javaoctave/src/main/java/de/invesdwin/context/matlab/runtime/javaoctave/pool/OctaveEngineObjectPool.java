package de.invesdwin.context.matlab.runtime.javaoctave.pool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import javax.inject.Named;

import org.springframework.beans.factory.FactoryBean;

import de.invesdwin.context.matlab.runtime.javaoctave.pool.internal.OctaveEnginePoolableObjectFactory;
import de.invesdwin.util.assertions.Assertions;
import de.invesdwin.util.collections.iterable.buffer.BufferingIterator;
import de.invesdwin.util.collections.iterable.buffer.IBufferingIterator;
import de.invesdwin.util.concurrent.Executors;
import de.invesdwin.util.concurrent.Threads;
import de.invesdwin.util.concurrent.WrappedExecutorService;
import de.invesdwin.util.concurrent.pool.commons.ACommonsObjectPool;
import de.invesdwin.util.time.date.FDate;
import de.invesdwin.util.time.duration.Duration;
import dk.ange.octave.OctaveEngine;

@ThreadSafe
@Named
public final class OctaveEngineObjectPool extends ACommonsObjectPool<OctaveEngine>
        implements FactoryBean<OctaveEngineObjectPool> {

    public static final OctaveEngineObjectPool INSTANCE = new OctaveEngineObjectPool();

    private final WrappedExecutorService timeoutMonitorExecutor = Executors
            .newFixedCallerRunsThreadPool(getClass().getSimpleName() + "_timeout", 1);
    @GuardedBy("this")
    private final IBufferingIterator<OctaveEngineWrapper> octaveEngineRotation = new BufferingIterator<OctaveEngineWrapper>();

    private OctaveEngineObjectPool() {
        super(OctaveEnginePoolableObjectFactory.INSTANCE);
        timeoutMonitorExecutor.execute(new OctaveEngineTimoutMonitor());
    }

    @Override
    protected synchronized OctaveEngine internalBorrowObject() {
        if (octaveEngineRotation.isEmpty()) {
            return factory.makeObject();
        }
        final OctaveEngineWrapper octaveEngine = octaveEngineRotation.next();
        if (octaveEngine != null) {
            return octaveEngine.getOctaveEngine();
        } else {
            return factory.makeObject();
        }
    }

    @Override
    public synchronized int getNumIdle() {
        return octaveEngineRotation.size();
    }

    @Override
    public synchronized Collection<OctaveEngine> internalClear() {
        final Collection<OctaveEngine> removed = new ArrayList<OctaveEngine>();
        while (!octaveEngineRotation.isEmpty()) {
            removed.add(octaveEngineRotation.next().getOctaveEngine());
        }
        return removed;
    }

    @Override
    protected synchronized OctaveEngine internalAddObject() {
        final OctaveEngine pooled = factory.makeObject();
        octaveEngineRotation.add(new OctaveEngineWrapper(factory.makeObject()));
        return pooled;
    }

    @Override
    protected synchronized void internalReturnObject(final OctaveEngine obj) {
        octaveEngineRotation.add(new OctaveEngineWrapper(obj));
    }

    @Override
    protected void internalInvalidateObject(final OctaveEngine obj) {
        //Nothing happens
    }

    @Override
    protected synchronized void internalRemoveObject(final OctaveEngine obj) {
        octaveEngineRotation.remove(new OctaveEngineWrapper(obj));
    }

    private class OctaveEngineTimoutMonitor implements Runnable {
        @Override
        public void run() {
            try {
                while (true) {
                    Threads.throwIfInterrupted();
                    TimeUnit.MILLISECONDS.sleep(100);
                    synchronized (OctaveEngineObjectPool.this) {
                        if (!octaveEngineRotation.isEmpty()) {
                            for (final OctaveEngineWrapper octaveEngine : octaveEngineRotation.snapshot()) {
                                if (octaveEngine.isTimeoutExceeded()) {
                                    Assertions.assertThat(octaveEngineRotation.remove(octaveEngine)).isTrue();
                                }
                            }
                        }
                    }
                }
            } catch (final InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static final class OctaveEngineWrapper {

        private final OctaveEngine octaveEngine;
        private final FDate timeoutStart;

        OctaveEngineWrapper(final OctaveEngine octaveEngine) {
            this.octaveEngine = octaveEngine;
            this.timeoutStart = new FDate();
        }

        public OctaveEngine getOctaveEngine() {
            return octaveEngine;
        }

        public boolean isTimeoutExceeded() {
            return new Duration(timeoutStart, new FDate()).isGreaterThan(Duration.ONE_MINUTE);
        }

        @Override
        public int hashCode() {
            return octaveEngine.hashCode();
        }

        @Override
        public boolean equals(final Object obj) {
            if (obj instanceof OctaveEngineWrapper) {
                final OctaveEngineWrapper cObj = (OctaveEngineWrapper) obj;
                return octaveEngine.equals(cObj.getOctaveEngine());
            } else if (obj instanceof OctaveEngine) {
                return octaveEngine.equals(obj);
            } else {
                return false;
            }
        }

    }

    @Override
    public OctaveEngineObjectPool getObject() {
        return INSTANCE;
    }

    @Override
    public Class<?> getObjectType() {
        return OctaveEngineObjectPool.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
