package de.io;

import de.io.submission.Payload;
import de.io.submission.UploadPayload;
import de.monitoring.ExceptionHandler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * SocketStream in io (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @version ...
 * @date 26.11.2020
 **/
public class SocketStream implements Stream {

    private final Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    private boolean open = false;
    private Payload input;
    private ExceptionHandler exceptionHandler;

    public SocketStream(Socket socket) {
        this.socket = socket;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException exception) {
            handleException();
        }
    }

    @Override
    public SocketStream handle() {
        try {
            if (open) {
                input = (Payload) inputStream.readObject();
            }
        } catch (Exception exception) {
            handleException();
        }
        return this;
    }

    @Override
    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            handleException();
        }
    }

    @Override
    public boolean alive() {
        return socket.isConnected();
    }

    @Override
    public Stream open() {
        open = true;
        return this;
    }

    @Override
    public Payload input() {
        return input;
    }

    @Override
    public boolean closed() {
        return socket.isClosed();
    }

    @Override
    public void out(UploadPayload packet) {
        if (open) {
            try {
                outputStream.writeObject(packet);
                outputStream.flush();
            } catch (IOException e) {
                handleException();
            }
        }
    }

    public void addExceptionHandler(ExceptionHandler exceptionHandler) {
        this.exceptionHandler = exceptionHandler;
    }

    @Override
    public void handleException() {
        exceptionHandler.onAction();
    }

}
