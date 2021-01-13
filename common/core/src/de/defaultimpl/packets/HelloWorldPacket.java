package de.defaultimpl.packets;

import de.api.Pair;
import de.api.network.io.Address;
import de.api.network.packet.Packet;
import de.api.network.packet.types.PacketType;
import de.api.security.certificate.keys.PrivateKey;
import de.api.security.certificate.keys.PublicKey;

public class HelloWorldPacket implements Packet {

    private final PacketType packetType;
    private final Address address;
    private final byte[] data;

    public HelloWorldPacket(PacketType packetType, Address address, byte[] data) {

        this.packetType = packetType;
        this.address = address;
        this.data = data;
    }

    @Override
    public PacketType getProtocol() {
        return packetType;
    }

    @Override
    public Address getAddress() {
        return address;
    }

    @Override
    public byte[] getData() {
        return data;
    }

    @Override
    public Pair<PublicKey, PrivateKey> getKeys() {
        return null;
    }

    @Override
    public String getTag() {
        return null;
    }

    @Override
    public void setKeys(Pair<PublicKey, PrivateKey> publicKey) {}

    @Override
    public HelloWorldPacket setPublicTag(String publicTag) {
        return this;
    }
}
