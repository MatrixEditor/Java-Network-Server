package de.io.packet.submission;

import de.api.Pair;
import de.api.security.certificate.keys.PrivateKey;
import de.api.security.certificate.keys.PublicKey;

import java.io.Serializable;

/**
 * Java-Network-Server in de.jns.io
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public interface Payload extends Serializable {

    byte[] getData();

    Pair<PublicKey, PrivateKey> getKeys();

    String getTag();

    void setKeys(Pair<PublicKey, PrivateKey> publicKey);

    Payload setPublicTag(String publicTag);
}
