package de.jns.defaultimpl;

import de.jns.Macro;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * DefaultMacro in de.jns (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class DefaultMacro implements Macro {

    private final AtomicBoolean open = new AtomicBoolean(false);

    @Override
    public void exec(Runnable runnable) {
        if (open.get()) runnable.run();
    }

    @Override
    public void execAsync(Runnable runnable) {
        if (open.get()) CompletableFuture.runAsync(runnable, Executors.newSingleThreadExecutor());
    }

    @Override
    public boolean isOpen() {
        return open.get();
    }

    @Override
    public void close() {
        if (open.get() && state() != Thread.State.TERMINATED) Thread.currentThread().interrupt();
    }

    @Override
    public Macro open() {
        open.set(true);
        return this;
    }

    @Override
    public synchronized Macro sync() {
        synchronized (this) {
            return this;
        }
    }

    @Override
    public Thread.State state() {
        return Thread.currentThread().getState();
    }
}
