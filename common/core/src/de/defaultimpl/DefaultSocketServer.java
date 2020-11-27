package de.defaultimpl;

import de.server.SocketServer;
import de.annotations.Authors;
import de.io.SocketStream;
import de.io.channel.IHandler;
import de.io.submission.Payload;
import de.io.submission.UploadPayload;

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
public class DefaultSocketServer extends SocketServer<Payload, UploadPayload> {

    private ServerSocket serverSocket;

    public DefaultSocketServer(int port) {
        super(port);
        asyncRunner.open();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            LOGGER.log(Level.WARNING, "Socket creation failed or invalid port");
        }
    }

    @Override
    public SocketServer<Payload, UploadPayload> start() {
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
    public void erase(IHandler<Payload, UploadPayload> channelHandler) {

    }

    @Override
    public void onAction(String ctx) {

    }
}
