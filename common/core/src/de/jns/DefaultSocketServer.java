package de.jns;

import de.jns.io.DefaultCertificateHandler;
import de.jns.io.DefaultFirewall;
import de.jns.io.SocketStream;
import de.jns.io.channel.ChannelAdapter;
import de.jns.io.channel.DefaultChannelHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;

/**
 * DefaultSocketServer in de.jns (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class DefaultSocketServer extends SocketServer<DefaultChannelHandler, SocketStream> {

    private ServerSocket serverSocket;


    public DefaultSocketServer(int port) {
        super(port);
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Socket creation failed or invalid port");
        }
    }

    @Override
    public void addExceptionHandler(DefaultChannelHandler channelHandler, Runnable eHandler) {
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
            LOGGER.log(Level.INFO, "New InputHandler");
            addExceptionHandler(inputHandler, () -> inputHandler.monitor(
                    () -> erase(inputHandler)));
            channelHandlers.put(inputHandler.getAddressees(), inputHandler);
            macro.exec(inputHandler::open);
            macro.execAsync(() -> inputHandler.input().loop());
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
            for (String s : e) {
                if (s.equals(client)) {
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
}
