package de.jns;

import de.jns.defaultimpl.DefaultMacro;
import de.jns.io.Address;
import de.jns.io.Stream;
import de.jns.io.channel.ChannelHandler;
import de.jns.monitoring.ExceptionHandler;
import de.jns.monitoring.LoggerFactory;

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
 * @param <C> A class that extends the ChannelHandler with a class of
 *           the Stream-interface
 * @param <S> A class that implements the Stream interface
 **/
public abstract class SocketServer<C extends ChannelHandler<S>, S extends Stream> {

    /**
     * A HashMap which stores every connection made by a request from
     * a client side application.
     * Pos 0: Server -- Address.
     * Pos 1: Client -- Address.
     */
    protected final HashMap<Address[], C> channelHandlers = new LinkedHashMap<>();

    protected static final Logger LOGGER = LoggerFactory.getLogger(SocketServer.class);

    protected final Macro macro = new DefaultMacro();

    protected final int PORT;

    public SocketServer(int port){
        PORT = port;
    }

    public abstract void addExceptionHandler(C channelHandler, ExceptionHandler eHandler);

    public abstract SocketServer<C, S> start();

    public abstract void loop();

    public abstract void stop();

    public abstract Socket accept();

    public abstract void erase(C channelHandler);

    public abstract String toString();

}

