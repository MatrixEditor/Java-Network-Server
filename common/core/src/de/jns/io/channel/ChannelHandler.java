package de.jns.io.channel;

import de.jns.io.submission.Payload;
import de.jns.io.Stream;
import de.jns.io.submission.UploadPayload;

/**
 * Java-Network-Server in io
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public interface ChannelHandler<T extends Stream> {

    Channel<T> channel();

    ChannelHandler<T> input();

    ChannelHandler<T> open();

    void output(UploadPayload msg);

    void loop();

    boolean check();

    <C extends Channel<T>> ChannelHandler<T> add(C channel);

    default void handle(Payload input) {
        handleAfter(input);
    }

    void handleAfter(Payload input);
}
