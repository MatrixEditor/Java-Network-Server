package packet;


import packet.types.PacketType;

public abstract class Packet implements Container {

    public static final Packet NULL_PACKET = nullPacket();

    private final String source;
    private final String destination;
    private final PacketType packetType;
    private final byte[] data;

    public Packet(String source, String destination,
                  PacketType packetType, byte[] data) {

        this.source = source;
        this.destination = destination;
        this.packetType = packetType;
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }

    public PacketType getProtocol() {
        return packetType;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    public static Packet nullPacket() {
        return new Packet(null, null, PacketType.NONE, null) {
            public PacketType getProtocol() {
                return super.getProtocol();
            }
        };
    }
}
