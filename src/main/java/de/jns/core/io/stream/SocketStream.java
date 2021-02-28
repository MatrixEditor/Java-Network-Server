package de.jns.core.io.stream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class SocketStream implements Stream {

    private final Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private boolean open = false;
    private Object input;

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
                input = inputStream.readObject();
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
    public Object input() {
        return input;
    }

    @Override
    public boolean closed() {
        return socket.isClosed();
    }

    @Override
    public void out(Object packet) {
        if (open) {
            try {
                outputStream.writeObject(packet);
                outputStream.flush();
            } catch (IOException e) {
                handleException();
            }
        }
    }


    @Override
    public void handleException() {}

    public static Socket getSocket(String host, int port) {
        try {
            return new Socket(host, port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getSocket(host, 5000);
    }

    public InetAddress getSocketAddress() {
        return socket.getInetAddress();
    }
}