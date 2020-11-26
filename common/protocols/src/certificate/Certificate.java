package certificate;


import certificate.keys.PublicKey;

import java.io.Serializable;

public abstract class Certificate implements Serializable {

    final PublicKey publicKey;

    final String[] format; //infos(name, text)

    public Certificate(PublicKey publicKey, String[] format) {
        this.publicKey = publicKey;
        this.format = format;
    }

    public abstract boolean isTrustable();//for start always true

    public String[] getFormat() {
        return format;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
