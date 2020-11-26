package de.jns.io.channel;

import de.jns.io.submission.Payload;
import de.jns.io.Stream;

/**
 * Java-Network-Server in io
 * <p>
 * A Channel maps a class that implements the {@link Stream} interface to have control
 * over a Stream. Also here the StreamType is set, either <code>Upstream or Downstream
 *
 * @author MatrixEditor
 * @version ...
 * @date 26.11.2020
 **/
public interface Channel<T> {
    /**
     * A StreamType is important to decide if there is incoming Data or if there only
     * is an output.
     */
    enum StreamType {
        UPSTREAM,
        DOWNSTREAM;
    }

    /**
     * Here the instance of the mapped Stream is given back to a higher class.
     * @return the mapped Stream
     */
    T stream();

    /**
     * To collect any input, this method is important.
     * @return the input from the mapped Stream
     */
    Payload input();

    /**
     * @return the StreamType, see implementation for more information
     */
    StreamType streamtype();

    /**
     * Usually the caught Exception in the stream is given into this method.
     * @return the exception class name
     */
    String exception();

    /**
     * To slow down a Thread, where this Channel is located, this method is referenced.
     * @return an instance of this interface
     */
    default Channel<T> sync() {
        synchronized (Channel.class) {
            return this;
        }
    }
}
