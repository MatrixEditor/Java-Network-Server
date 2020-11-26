package de.jns.io.channel;

import de.jns.io.submission.Payload;
import de.jns.io.submission.PayloadFactory;
import de.jns.io.SocketStream;
import de.jns.io.submission.UploadPayload;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
DefaultChannelHandler in io.channel (Java-Network-Server)

Class description...

@author
@version ...
@date 26.11.2020
**/
public class DefaultChannelHandler extends ChannelHandlerAdapter<SocketStream> {

    private final PayloadFactory payloadFactory = new PayloadFactory();

    private final String[] Addressees = new String[2];

    private final Channel<SocketStream> inputChannel;
    private boolean print = false;

    public DefaultChannelHandler(Channel<SocketStream> channel) {
            inputChannel = channel;
        }

    @Override
    public void loop() {
        CompletableFuture.runAsync(() -> {
            while (channel().stream().alive() && !channel().stream().closed()) {
                input();
            }
        }, Executors.newSingleThreadExecutor());
    }

    @Override
    public boolean check() {
        return !channel().stream().closed();
    }

    @Override
    public void handleAfter(Payload input) {
        //TODO
        if (print) {
            System.out.println(input.toString());
        }
        //output(payloadFactory.createUpload(5, input.getPublicKey(), Packet.nullPacket()));
    }


    @Override
    public <C extends ChannelWorker<SocketStream>>
    DefaultChannelHandler add(C channel) {
        channels.add(channel);
        return this;
    }

    @Override
    public <C extends ChannelWorker<SocketStream>>
    DefaultChannelHandler addFirst(C channel) {
        channels.add(0, channel);
        return this;
    }

    @Override
    public <C extends ChannelWorker<SocketStream>>
    DefaultChannelHandler addLast(C channel) {
        channels.add(channels.size(), channel);
        return this;
    }

    @Override
    public ChannelWorker<SocketStream> channel(int pos) {
        return channels.get(pos);
    }

    @Override
    public Channel<SocketStream> channel() {
        return inputChannel;
    }

    @Override
    public DefaultChannelHandler input() {
        if (!channel().stream().closed()) handle(filter(channel().input(), 0));
        return this;
    }

    /**
     *  ?? handle Method importance ??
     *
     * @param input - given input from the Stream
     * @param i - the index of the Channel list
     * @return the input
     */
    @Override
    public Payload filter(Payload input, int i) {
        if (!channel().stream().closed()) {
            if (!(i >= channels.size())) {
                ChannelWorker.Restitution restitution = channel(i).act(input);
                switch (restitution) {
                    case CONTINUE:
                        if ((i + 1) >= channels.size()) return input;
                        return filter(input, ++i);
                    case STOP:
                        return input;
                    default:
                        throw new NullPointerException();
                }
            }  else return input;
        } else return null;
    }

    @Override
    public void onPrint() {
        print = true;
    }

    @Override
    public DefaultChannelHandler open() {
        inputChannel.stream().open();
        return this;
    }

    public void monitor(Runnable refuseConn) {
        if ("SocketException".equals(channel().exception())) {
            channel().stream().close();
            refuseConn.run();
        } else System.out.println(channel().exception());

    }

    public String[] getAddressees() {
        return Addressees;
    }

    @Override
    public void output(UploadPayload p) {
        channel().stream().out(p);
    }

    public PayloadFactory getPayloadFactory() {
        return payloadFactory;
    }
}


