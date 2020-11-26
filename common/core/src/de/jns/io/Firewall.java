package de.jns.io;

import de.jns.io.channel.ChannelWorker;
import de.jns.io.submission.Payload;
import de.jns.validation.Validator;

import java.util.logging.Logger;

/**
 * Firewall in PACKAGE_NAME (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @version ...
 * @date 26.11.2020
 **/
public abstract class Firewall<T extends Stream>
        extends ChannelWorker<T> {

    protected static final Logger LOGGER = Logger.getLogger(Firewall.class.getSimpleName());

    protected Validator validator;

    public abstract boolean check(Payload input);

    public abstract Validator validator();



}
