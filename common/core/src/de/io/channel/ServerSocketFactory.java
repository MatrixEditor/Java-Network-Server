package de.io.channel;


import de.api.monitoring.ThrowableFactory;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * ServerSocketFactory in de.io.channel (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 27.11.2020
 **/
public class ServerSocketFactory
        implements ThrowableFactory<ServerSocket, IOException> {

    @Override
    public ServerSocket create() throws IOException {
        return new ServerSocket();
    }
}
