package de.defaultimpl;


import de.annotations.Authors;
import de.io.Address;
import de.io.SocketStream;
import de.io.channel.ChannelHandlerAdapter;
import de.io.channel.ChannelWorker;
import de.io.packet.submission.Payload;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;

/**
DefaultChannelHandler in io.channel (Java-Network-Server)

Class description...

@version ...
@date 26.11.2020
**/
@Authors
public class DefaultChannelHandler extends
        ChannelHandlerAdapter<SocketStream, Payload, Payload> {

    private boolean echo = false;

    public DefaultChannelHandler(SocketStream channel) {
        super(channel);
    }

    public void loop() {
        CompletableFuture.runAsync(() -> {
            while (stream().alive() && !stream().closed()) {
                in();
            }
        }, Executors.newSingleThreadExecutor());
    }

    @Override
    public ChannelWorker channel(int pos) {
        return channels.get(pos);
    }

    @Override
    public void in() {
        if (!stream().closed()) {
            Payload payload = stream().handle().input();
            payload = filter(payload, 0);
            handle(payload);
        }
    }

    @Override
    public void handle(Payload input) {
        System.out.println(input.toString());
    }

    @Override
    public void out(Payload out) {
        stream().out(out);
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
        if (!stream().closed()) {
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

    public DefaultChannelHandler open() {
        stream().open();
        return this;
    }

    public Address getAddressees() {
        return Addressees;
    }


}


