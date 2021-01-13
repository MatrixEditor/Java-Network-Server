package de.defaultimpl.packets;

import de.api.Pair;
import de.api.network.io.Address;
import de.api.network.packet.Packet;
import de.api.network.packet.types.PacketType;
import de.api.security.certificate.Certificate;
import de.api.security.certificate.keys.PrivateKey;
import de.api.security.certificate.keys.PublicKey;

public class CertificatePacket implements Packet {

    private final Certificate certificate;
    private final Type type;
    private final PacketType packetType;
    private final Address address;
    private final byte[] data;
    private Pair<PublicKey, PrivateKey> keys;
    private String tag;

    public CertificatePacket(Certificate certificate, Type type, PacketType packetType, Address address, byte[] data) {
        this.certificate = certificate;
        this.type = type;
        this.packetType = packetType;
        this.address = address;
        this.data = data;
    }

    //TODO
    public enum Type {
        QE,
        INI,
        RE;
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
        return keys;
    }

    @Override
    public String getTag() {
        return tag;
    }

    @Override
    public void setKeys(Pair<PublicKey, PrivateKey> k) {
        this.keys = k;
    }

    @Override
    public CertificatePacket setPublicTag(String publicTag) {
        this.tag = publicTag;
        return this;
    }

    public Type getType() {
        return type;
    }

    public Certificate getCertificate() {
        return certificate;
    }
}
