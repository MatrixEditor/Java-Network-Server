package de.jns.io;


import certificate.Certificate;
import certificate.keys.PublicKey;
import de.jns.io.channel.ChannelWorker;

/**
 * CertificateHandler in io (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public abstract class CertificateHandler<T extends Stream>
        extends ChannelWorker<T> {

    private PublicKey publicKey;

    private final String[] knownCertificates;

    public CertificateHandler() {
        knownCertificates = null;
    }

    public CertificateHandler(String[] knownCertificates) {

        this.knownCertificates = knownCertificates;
    }

    public Certificate handle(Certificate certificate) {
        boolean knownCAs = knownCertificates == null;

        if (knownCAs) {
            if (certificate.isTrustable()) {
                return accept(certificate);
            }
        } else {
            if (certificate.isTrustable() && isKnown(certificate.getFormat())) {
                return accept(certificate);
            }
        }
        return null;
    }

    public abstract Certificate accept(Certificate certificate);

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public boolean isKnown(String[] format) {
        //// FIXME
        return true;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }
}

