package de.io.channel;

import de.io.Stream;

/**
 * Java-Network-Server in de.io.channel
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 27.11.2020
 **/
public interface IHandler<I, O> {

    void in();

    void handle(I input);

    void out(O out);

}