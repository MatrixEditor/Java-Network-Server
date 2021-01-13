package de.io.channel;



import de.io.Address;
import de.io.Stream;
import de.io.packet.submission.Payload;

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

    protected final ArrayList<ChannelWorker> channels = new ArrayList<>();
    protected Address Addressees;
    protected final T stream;

    protected ChannelHandlerAdapter(T stream) {
        this.stream = stream;
    }

    public T stream() {return stream;}

    public abstract ChannelWorker channel(int pos);

    public void close() {}

    public abstract Payload filter(Payload input, int i);

}