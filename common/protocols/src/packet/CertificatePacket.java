package packet;


import certificate.Certificate;
import certificate.keys.PublicKey;
import packet.types.PacketType;

public class CertificatePacket extends Packet {

    public enum Type {
        QE,
        INI,
        RE;
    }

    private final Certificate certificate;

    private final byte[] key;

    private Type type;

    public CertificatePacket(String source, String destination, Certificate certificate, byte[] key) {
        super(source, destination, PacketType.CERTIFICATE, null);
        this.certificate = certificate;
        this.key = key;
        this.type = Type.INI;
    }

    public Certificate getCertificate() {
        return certificate;
    }

    public PublicKey getPublicKey() {
        return certificate.getPublicKey();
    }

    public byte[] getKey() {
        return key;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
