package de.defaultimpl;


import de.api.annotations.Authors;
import de.api.monitoring.Factory;
import de.api.network.io.SocketStream;
import de.api.network.io.channel.IHandler;
import de.api.network.packet.submission.Payload;
import de.api.server.SocketServer;

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
public class DefaultSocketServer extends SocketServer<Payload, Payload> {

    private final ServerSocket serverSocket;

    public DefaultSocketServer(int port) {
        super(port);
        asyncRunner.open();
        serverSocket = Factory.createServerSocket(port);
    }

    @Override
    public SocketServer<Payload, Payload> run() {
        return this;
    }

    @Override
    public void loop() {
        while (!serverSocket.isClosed()) {
            DefaultChannelHandler channelHandler = new DefaultChannelHandler(
                    new SocketStream(accept())
            );
            channelHandlers.put(channelHandler.getAddressees(), channelHandler);
            LOGGER.log(Level.SEVERE, "New InputHandler");
            asyncRunner.execAsync(() ->  channelHandler.open().loop());
        }
    }


    @Override
    public void stop() {
        if (asyncRunner.isOpen()) {
            asyncRunner.close();
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

    @Override
    public void erase(IHandler<Payload, Payload> channelHandler) {

    }

    @Override
    public void onAction(String ctx) {

    }
}
