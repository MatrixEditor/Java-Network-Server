package packet;


import packet.types.PacketType;

public class KeepAlivePacket extends Packet {

    public KeepAlivePacket(String source, String destination) {

        super(source, destination, PacketType.KEEP_ALIVE, "keepAlive".getBytes());
    }
}
