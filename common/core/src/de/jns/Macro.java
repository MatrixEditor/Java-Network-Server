package de.jns;


import java.io.Closeable;

/**
 * commhawk in de.commhawk.cloud.server.worker
 * <p>
 * Class description...
 *
 * @author Leonard, Jan
 * @version ...
 * @date 04.11.2020
 **/
public interface Macro extends Closeable {

    void exec(Runnable runnable);

    void execAsync(Runnable runnable);

    boolean isOpen();

    void close();

    Macro open();

    Macro sync();

    Thread.State state();

}
