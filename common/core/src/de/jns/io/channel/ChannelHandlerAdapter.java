package de.jns.io.channel;

import de.jns.io.submission.Payload;
import de.jns.io.Stream;

import java.util.ArrayList;

/**
 * ChannelHandlerAdapter in io (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public abstract class ChannelHandlerAdapter<T extends Stream>
        implements ChannelHandler<T> {

    protected final ArrayList<ChannelWorker<T>> channels = new ArrayList<>();

    public abstract Channel<T> channel(int pos);

    public abstract ChannelHandler<T> input();

    public abstract ChannelHandler<T> open();

    public <C extends Channel<T>> ChannelHandler<T> add(C channel) {return this;}

    public <C extends ChannelWorker<T>> ChannelHandler<T> add(C channel) {return this;}

    public <C extends ChannelWorker<T>> ChannelHandler<T> addFirst(C channel) {return this;}

    public <C extends ChannelWorker<T>> ChannelHandler<T> addLast(C channel){return this;}

    public void close() {}

    public abstract Payload filter(Payload input, int i);

    public void handleNonePacket() {}

    public abstract void onPrint();
}

