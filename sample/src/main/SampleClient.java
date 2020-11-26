package main;


import de.jns.io.SocketStream;
import de.jns.io.channel.ChannelAdapter;
import de.jns.io.channel.DefaultChannelHandler;
import de.jns.monitoring.ConsoleFactory;
import de.jns.monitoring.ConsoleHandler;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
 * SampleClient in java.main (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class SampleClient {

    public static void main(String[] args) {
        try {
            DefaultChannelHandler default_handler = new DefaultChannelHandler(
                    new ChannelAdapter<>(new SocketStream(new Socket("localhost", 23500)))
            );
            default_handler.open();
            default_handler.onPrint();
            CompletableFuture.runAsync(() -> default_handler.input().loop(),
                    Executors.newSingleThreadExecutor());

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
