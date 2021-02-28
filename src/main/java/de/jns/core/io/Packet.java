package de.jns.core.io;


public abstract class Packet implements Payload<byte[]> {

    protected String source, destination;
    public Packet(String s, String d) {
        source = s;
        destination = d;
    }
    public abstract Protocol getProtocol();

    public abstract void setProtocol(Protocol protocol);

    public String getProtocolName() {
        return getProtocol().getClass().getSimpleName();
    }

}
