package main;


import de.jns.io.SocketStream;
import de.jns.io.channel.ChannelAdapter;
import packet.Packet;

import java.io.IOException;
import java.net.Socket;
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
            SampleClientChannel sampleClientChannel = new SampleClientChannel(
                    new ChannelAdapter<>(
                            new SocketStream(new Socket("localhost", 3000))
                    )
            );
            sampleClientChannel.open();
            CompletableFuture.runAsync(() -> sampleClientChannel.input().loop(),
                    Executors.newSingleThreadExecutor());
            Thread.sleep(5L);
            sampleClientChannel.output(sampleClientChannel.getPayloadFactory().createUpload(4, null, Packet.NULL_PACKET));
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }



    }
}
