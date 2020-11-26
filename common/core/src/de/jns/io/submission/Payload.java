package de.jns.io.submission;

import certificate.keys.PublicKey;
import packet.Packet;

import java.io.IOException;
import java.io.Serializable;

/**
 * Java-Network-Server in de.jns.io
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public interface Payload extends Serializable {

    void verify() throws IOException;

    Packet[] getPackets();

    PublicKey getPublicKey();

    String getPublicTag();

    void addPublicKey(PublicKey publicKey);

    Payload addPacket(Packet packet);

    Payload setPublicTag(String publicTag);
}
