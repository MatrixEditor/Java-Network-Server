package de.jns.io.submission;

import certificate.keys.PublicKey;
import de.jns.monitoring.LoggerFactory;
import packet.Packet;

import java.io.IOException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * PayloadFactory in de.jns.io (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class PayloadFactory {

    private static final Logger logger = LoggerFactory
            .getLogger(PayloadFactory.class);


    public UploadPayload createUpload(int tag, PublicKey publicKey,
                                      Packet ... packets) {
        var payload = new UploadPayload(packets);
        payload.setPublicTag(createPublicTag(tag))
                .addPublicKey(publicKey);
        /*
        try {
            payload.verify();
        } catch (IOException e) {
            logger.log(Level.WARNING, e.getLocalizedMessage());
        }
         */
        return payload;
    }

    private String createPublicTag(int count) {
        SecureRandom secureRandom = new SecureRandom();
        final String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        StringBuilder builder = new StringBuilder(count);
        for (int i = 0; i < count; i++) {
            builder.append(AB.charAt(secureRandom.nextInt(AB.length())));
        }
        return builder.toString();
    }
}
