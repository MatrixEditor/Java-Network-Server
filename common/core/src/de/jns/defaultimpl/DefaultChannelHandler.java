package de.jns.defaultimpl;

import de.jns.annotations.Authors;
import de.jns.io.Address;
import de.jns.io.channel.Channel;
import de.jns.io.channel.ChannelHandlerAdapter;
import de.jns.io.channel.ChannelWorker;
import de.jns.io.submission.Payload;
import de.jns.io.SocketStream;
import de.jns.io.submission.UploadPayload;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
DefaultChannelHandler in io.channel (Java-Network-Server)

Class description...

@version ...
@date 26.11.2020
**/
@Authors
public class DefaultChannelHandler extends ChannelHandlerAdapter<SocketStream> {

    private boolean echo = false;

    public DefaultChannelHandler(Channel<SocketStream> channel) {
        super(channel);
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
        System.out.println(input.toString());
        if (echo) output(payloadFactory.createUpload(5, input.getPublicKey(), input.getPackets()));

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
    public ChannelWorker<SocketStream> channel(int pos) {
        return channels.get(pos);
    }

    @Override
    public Channel<SocketStream> channel() {
        return inputChannel;
    }

    @Override
    public DefaultChannelHandler input() {
        if (!channel().stream().closed()) {
            Payload payload = channel().input();
            payload = filter(payload, 0);
            handle(payload);
        }
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
                switch (channel(i).act(input)) {
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

    public void onEcho() {echo = true;}

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

    public Address[] getAddressees() {
        return Addressees;
    }

    @Override
    public void output(UploadPayload p) {
        channel().stream().out(p);
    }

}


