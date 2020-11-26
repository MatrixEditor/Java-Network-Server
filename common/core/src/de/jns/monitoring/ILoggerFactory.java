package de.jns.monitoring;

import java.util.logging.Logger;

/**
 * Java-Network-Server in PACKAGE_NAME
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public interface ILoggerFactory {
    Logger getLogger(String name);
}
