package de.jns.core.server;

import de.jns.core.io.stream.Stream;
import de.jns.core.io.stream.StreamReader;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class SocketServer<I extends Serializable, O extends Serializable> {

    protected final List<StreamReader<Stream, I, O>> streamReaders = new ArrayList<>();

    protected final int PORT;

    public SocketServer(int port){
        PORT = port;
    }

    public abstract SocketServer<I, O> run();

    public abstract void loop();

    public abstract void stop();

    public abstract void erase(StreamReader<Stream, I, O> channelHandler);

}

