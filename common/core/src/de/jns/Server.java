package de.jns;

import de.jns.monitoring.ConsoleHandler;
import de.jns.monitoring.LoggerFactory;
import main.SampleServer;

import java.util.Random;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Server in de.jns (Java-Network-Server)
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public abstract class Server {

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
    protected final ConsoleHandler consoleHandler = new ConsoleHandler(new Scanner(System.in));

    protected final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    protected ServerType serverType;

    protected ServerMode serverMode;

    protected Server() {}

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
    public abstract ServerType type();

    /**
     * The name mapped as an integer.
     */
    public abstract int name();


    public abstract void setMode(ServerMode mode);

}
