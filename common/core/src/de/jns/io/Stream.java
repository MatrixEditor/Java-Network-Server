package de.jns.io;

import de.jns.io.submission.Payload;
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
public interface Stream {
    /**
     * This close san open connection between two clients, either server/client or
     * client/client.
     */
    void close();

    /**
     * This checks if the socket is connected or not.
     * @return true if the Socket is connected
     */
    boolean alive();


    /**
     * To open a Stream, this method is used. The state, if a stream is opened or not
     * can be represented through a boolean variable. It is imported to open a stream
     * to receive input otherwise nothing happens.
     * @return current instance of this Interface
     */
    Stream open();

    /**
     * Here the Stream gets input with the usage of an {@link java.io.ObjectInputStream}
     * only if the Stream state is equal to {@code state=open}.
     * @return current instance of this Interface
     */
    Stream handle();

    /**
     * @return the input value, stored in a separate variable
     */
    Payload input();

    /**
     * To check if this Stream instance is opened this method is used.
     * @return true if the Stream is closed else false
     */
    boolean closed();

    /**
     * Here an Object is being sent out by using an {@link java.io.ObjectOutputStream} to a
     * specified client. Usually this Object is represented by an instance of a class
     * that extends a Packet.
     * @param packet the Object to send out
     */
    void out(UploadPayload packet);

    /**
     * In order to react to a closing event which comes from a ChannelHandler a runnable
     * task is activated when an error occurred.
     */
    void handleException();

    /**
     * This method returns a value, String, to give classes above the Exception type.
     * @return the class name of the {@link Exception}
     */
    String exception();
}
