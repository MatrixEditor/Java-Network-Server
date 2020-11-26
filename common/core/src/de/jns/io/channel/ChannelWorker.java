package de.jns.io.channel;

import de.jns.io.submission.Payload;
import de.jns.io.Stream;

/**
 * ChannelWorker in io (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author MatrixEditor
 * @version ...
 * @date 26.11.2020
 **/
public abstract class ChannelWorker<T extends Stream>
        implements Channel<T> {

    public enum Restitution {
        STOP,
        CONTINUE,
        ERROR;
    }

    public abstract Restitution act(Payload input, Runnable runnable);

    public abstract Restitution act(Payload input);

    public abstract void onError();

    @Override
    public T stream() {
        return null;
    }

    @Override
    public Payload input() {
        return null;
    }

    @Override
    public StreamType streamtype() {return null;}

    @Override
    public String exception() {return null;}

}
