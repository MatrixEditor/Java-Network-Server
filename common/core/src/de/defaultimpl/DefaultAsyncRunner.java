package de.defaultimpl;


import de.api.AsyncRunner;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Supplier;

/**
 * DefaultAsyncRunner in de.jns (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class DefaultAsyncRunner implements AsyncRunner {

    private final AtomicBoolean open = new AtomicBoolean(false);

    @Override
    public <E> E supplyAsync(Supplier<E> runnable) {
        if (open.get()) {
            try {
                return CompletableFuture.
                        supplyAsync(runnable, Executors.newSingleThreadExecutor()).get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return null;
            }
        } else return null;
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
    public AsyncRunner open() {
        open.set(true);
        return this;
    }

    @Override
    public synchronized AsyncRunner sync() {
        synchronized (this) {
            return this;
        }
    }

    @Override
    public Thread.State state() {
        return Thread.currentThread().getState();
    }
}
