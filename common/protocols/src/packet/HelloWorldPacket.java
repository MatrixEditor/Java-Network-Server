package packet;


import packet.types.PacketType;

public class HelloWorldPacket extends Packet {

    public HelloWorldPacket(String source, String destination) {
        super(source, destination, PacketType.HELLO_WORLD, "Hallo Welt!".getBytes());
    }

    public static HelloWorldPacket getHelloWorldPacket() {
        return new HelloWorldPacket(null, null);
    }
    public static HelloWorldPacket getHelloWorldPacket(String source, String destination) {
        return new HelloWorldPacket(source, destination);
    }
}
