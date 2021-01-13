package de.io.packet.submission;


import de.api.monitoring.logger.LoggerFactory;

import java.security.SecureRandom;
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
@Deprecated
public class PayloadFactory {

    private static final Logger logger = LoggerFactory
            .getLogger(PayloadFactory.class);


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
