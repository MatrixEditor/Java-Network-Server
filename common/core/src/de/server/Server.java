package de.server;

import de.api.AsyncRunner;
import de.api.ServerRunnable;
import de.api.WorkerGroup;
import de.api.monitoring.ConsoleFactory;
import de.api.monitoring.ConsoleHandler;
import de.api.monitoring.logger.LoggerFactory;

import javax.security.auth.Destroyable;
import java.util.Random;
import java.util.logging.Logger;

/**
 * Server in de.jns (Java-Network-Server)
 * @version ...
 * @date 26.11.2020
 **/
public abstract class Server implements Destroyable {

    /**
     * A generated unique String to identify the Server.
     */
    protected static final int TAG = new Random().nextInt();

    /**
     * The ID is supposed to be more important in later
     * progress.
     * Either to identify or to give the Server an Identity.
     */
    protected static final long ID = new Random().nextLong();

    /**
     * The {@link ConsoleHandler} listens for Input at the console
     * and execute a runnable command after getting some message.
     */
    protected final ConsoleHandler consoleHandler = ConsoleFactory.getEmptyConsoleHandler(System.in);
    protected final Logger LOGGER = LoggerFactory.getLogger(Server.class);
    protected final WorkerGroup<AsyncRunner> workerGroup = new WorkerGroup<>();
    protected ServerType serverType;
    protected ServerMode serverMode;

    /**
     * A simple Function to start the server.
     */
    protected abstract void power_init();

    /**
     * A simple Function to shut down the Server instance.
     */
    protected abstract void shutDown();

    /**
     * For later usage:
     *  -> to identify the server
     */
    public ServerType type() {return serverType;}

    public int name() {return TAG;};

    public void setMode(ServerMode mode) {serverMode = mode;}

    public Server add(ServerRunnable runnable, String command){
        consoleHandler.addAction(command, runnable);
        return this;
    }

}
