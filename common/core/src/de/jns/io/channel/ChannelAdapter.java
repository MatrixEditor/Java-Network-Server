package de.jns.io.channel;

import de.jns.io.Stream;
import de.jns.io.submission.Payload;

/**
 * ChannelAdapter in io (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class ChannelAdapter<T extends Stream> implements Channel<T> {

    private final T stream;

    public ChannelAdapter(T stream) {
        this.stream = stream;
    }

    @Override
    public T stream() {
        return stream;
    }

    @Override
    public Payload input() {
        synchronized (stream) {
            return stream.handle().input();
        }
    }

    @Override
    public StreamType streamtype() {
        return null;
    }

    @Override
    public String exception() {
        return stream().exception();
    }

    @Override
    public synchronized ChannelAdapter<T> sync() {
        synchronized (this) {
            return this;
        }
    }
}
