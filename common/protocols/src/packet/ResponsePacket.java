package packet;


import packet.types.PacketType;

public class ResponsePacket extends Packet {

    public ResponsePacket(String source, String destination, byte[] data) {

        super(source, destination, PacketType.RESPONSE, data);
    }

    @Override
    public PacketType getProtocol() {
        return PacketType.RESPONSE;
    }
}
