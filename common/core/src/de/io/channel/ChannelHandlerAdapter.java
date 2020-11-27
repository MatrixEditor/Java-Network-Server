package de.io.channel;

import de.io.Address;
import de.io.submission.Payload;
import de.io.Stream;
import de.io.submission.PayloadFactory;

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
public abstract class ChannelHandlerAdapter<T extends Stream, I, O>
        implements IHandler<I, O> {

    protected final ArrayList<ChannelWorker<T>> channels = new ArrayList<>();

    protected final PayloadFactory payloadFactory = new PayloadFactory();

    protected final Address[] Addressees = new Address[2];

    protected final T stream;

    protected ChannelHandlerAdapter(T stream) {
        this.stream = stream;
    }

    public T stream() {return stream;}

    public abstract ChannelWorker<T> channel(int pos);

    public void close() {}

    public abstract Payload filter(Payload input, int i);

}

