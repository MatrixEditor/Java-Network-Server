package de;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Java-Network-Server in de
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 27.11.2020
 **/
public interface Factory<E> {
    E create();

    static ObjectOutputStream createOutputStream(Socket socket){
        try {
            return new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    static ObjectInputStream createInputStream(Socket socket){
        try {
            return new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
