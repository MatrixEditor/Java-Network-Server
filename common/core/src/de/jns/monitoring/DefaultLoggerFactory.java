package de.jns.monitoring;

import java.util.logging.Logger;

/**
 * DefaultLoggerFactory in PACKAGE_NAME (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class DefaultLoggerFactory implements ILoggerFactory{
    @Override
    public Logger getLogger(String name) {
        return Logger.getLogger(name);
    }
}
