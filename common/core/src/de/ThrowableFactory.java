package de;


/**
 * Java-Network-Server in de
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 27.11.2020
 **/
public interface ThrowableFactory<T, E extends Throwable> {

    T create() throws E;

}
