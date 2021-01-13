package de.io.packet.types;

public enum PacketType {

    HELLO_WORLD("HelloWorldPacket"),
    KEEP_ALIVE("KeepAlivePacket"),
    RESPONSE("ResponsePacket"),
    CERTIFICATE("CertificatePacket"),
    QUERY("QueryPacket"),
    REMOTE("RemotePacket"),
    NONE("");

    private final String label;

    PacketType(String s) {
        label = s;
    }

    public String getPacketType() {
        return label;
    }
}
