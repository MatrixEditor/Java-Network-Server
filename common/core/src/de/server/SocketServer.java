package de.server;

import de.AsyncRunner;
import de.ServerRunnable;
import de.defaultimpl.DefaultAsyncRunner;
import de.io.Address;
import de.io.channel.IHandler;
import de.monitoring.LoggerFactory;

import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

/**
 * SocketServer in PACKAGE_NAME (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public abstract class SocketServer<I, O> implements ServerRunnable {

    protected static final Logger LOGGER = LoggerFactory.getLogger(SocketServer.class);

    /**
     * A HashMap which stores every connection made by a request from
     * a client side application.
     * Pos 0: Server -- Address.
     * Pos 1: Client -- Address.
     */
    protected final HashMap<Address[], IHandler<I, O>> channelHandlers = new LinkedHashMap<>();

    protected final AsyncRunner asyncRunner = new DefaultAsyncRunner();

    protected final int PORT;

    public SocketServer(int port){
        PORT = port;
    }

    public abstract SocketServer<I, O> run();

    public abstract void loop();

    public abstract void stop();

    public abstract Socket accept();

    public abstract void erase(IHandler<I, O> channelHandler);

}

