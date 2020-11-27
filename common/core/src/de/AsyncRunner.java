package de;


import java.io.Closeable;
import java.util.function.Supplier;

/**
 * commhawk in de.commhawk.cloud.server.worker
 * <p>
 * Class description...
 *
 * @author Leonard, Jan
 * @version ...
 * @date 04.11.2020
 **/
public interface AsyncRunner extends Closeable {

    <E> E supplyAsync(Supplier<E> runnable);

    void execAsync(Runnable runnable);

    boolean isOpen();

    void close();

    AsyncRunner open();

    AsyncRunner sync();

    Thread.State state();

}
