package de.jns.defaultimpl;

import de.jns.ServerMode;
import de.jns.SocketServer;
import de.jns.annotations.Authors;
import de.jns.io.Address;
import de.jns.io.SocketStream;
import de.jns.io.channel.ChannelAdapter;
import de.jns.monitoring.ExceptionHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

/**
 * DefaultSocketServer in de.jns (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @version ...
 * @date 26.11.2020
 **/
@Authors
public class DefaultSocketServer extends SocketServer<DefaultChannelHandler, SocketStream> {

    private ServerSocket serverSocket;

    private ServerMode serverMode;

    public DefaultSocketServer(int port) {
        super(port);
        macro.open();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Socket creation failed or invalid port");
        }
    }

    @Override
    public void addExceptionHandler(DefaultChannelHandler channelHandler, ExceptionHandler eHandler) {
        channelHandler.channel().stream().addExceptionHandler(eHandler);
    }

    @Override
    public SocketServer<DefaultChannelHandler, SocketStream> start() {
        return this;
    }

    @Override
    public void loop() {
        while (!serverSocket.isClosed()) {
            DefaultChannelHandler inputHandler = new DefaultChannelHandler(
                    new ChannelAdapter<>(
                            new SocketStream(accept())))
                    .add(new DefaultFirewall())
                    .add(new DefaultCertificateHandler());
            switch (serverMode) {
                case ECHO:
                    inputHandler.onEcho();
                    break;
                case NONE:
                default:
                    break;
            }
            LOGGER.log(Level.INFO, "New InputHandler");
            channelHandlers.put(inputHandler.getAddressees(), inputHandler);
            inputHandler.open();
            addExceptionHandler(inputHandler,
                    () -> inputHandler.monitor(
                        () -> erase(inputHandler)));
            macro.execAsync(
                    () -> inputHandler.input().loop());
        }
    }


    @Override
    public void stop() {
        if (macro.isOpen()) {
            macro.close();
        }
    }

    @Override
    public Socket accept() {
        try {
            return serverSocket.accept();
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, e.getMessage());
        }
        return null;
    }

    @SuppressWarnings("Unsafe")
    public void removeChannel(String client) {
        channelHandlers.keySet().forEach(e -> {
            for (Address s : e) {
                if (s.address().equals(client)) {
                    channelHandlers.remove(e);
                    break;
                }
            }
        });
    }

    @Override
    public void erase(DefaultChannelHandler channelHandler) {
        if (channelHandlers.containsValue(channelHandler)) {
            channelHandlers.remove(channelHandler.getAddressees());
        } else System.out.println(channelHandler.toString());
    }

    @Override
    public String toString() {
        return "\n+--SocketServer \n|\tport=" + PORT + "; " +
                "\n|\t(" + macro.toString() + ")" +
                "\n|\t(" + channelHandlers.toString() + ")";
    }

    public void setMode(ServerMode serverMode) {
        this.serverMode = serverMode;
    }
}
