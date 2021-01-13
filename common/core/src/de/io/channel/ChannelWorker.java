package de.io.channel;


import de.api.network.packet.submission.Payload;

/**
 * ChannelWorker in io (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author MatrixEditor
 * @version ...
 * @date 26.11.2020
 **/
public abstract class ChannelWorker {

    public enum Restitution {
        STOP,
        CONTINUE,
        ERROR;
    }

    public abstract Restitution act(Payload input, Runnable runnable);

    public abstract Restitution act(Payload input);

    public abstract void onError();

}
