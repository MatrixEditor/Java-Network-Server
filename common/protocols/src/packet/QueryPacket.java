package packet;


import packet.types.PacketType;

public class QueryPacket extends Packet {

    public QueryPacket(String source, String destination, byte[] query) {
        super(source, destination, PacketType.QUERY, query);
    }
}
