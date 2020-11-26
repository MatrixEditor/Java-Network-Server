package de.jns.io.submission;

import certificate.keys.PublicKey;
import packet.Packet;

import java.io.IOException;


/**
 * UploadPayload in de.jns.io (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class UploadPayload implements Payload {

    private PublicKey publicKey;

    private String publicTag;

    private final Packet[] packets;

    public UploadPayload(int max_size) {
        packets = new Packet[max_size];
    }

    public UploadPayload(Packet...packets1) {
        packets = packets1;
    }

    public void addPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public UploadPayload addPacket(Packet packet) {
        if (!full()) {
            packets[last()] = packet;
        }
        return this;
    }

    public UploadPayload setPublicTag(String publicTag) {
        this.publicTag = publicTag;
        return this;
    }

    @Override
    public void verify() throws IOException {
        throw new IOException("Temporary error");
    }

    public Packet[] getPackets() {
        return packets;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public String getPublicTag() {
        return publicTag;
    }

    private boolean full() {
        for (Packet packet : packets) {
            if (packet == null) {
                return false;
            }
        }
        return true;
    }

    private int last() {
        for (int i = 0; i < packets.length; i++) {
            if (packets[i] == null) {
                return i;
            }
        }
        return -1;
    }

}
