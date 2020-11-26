package de.jns.io.submission;

import packet.Packet;

import java.lang.annotation.*;

/**
 * Java-Network-Server in de.jns.io
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Packable {

    Class<? extends Packet> payload() default Packet.class;
}
