package de.jns.monitoring;

import java.util.logging.Logger;

/**
 * LoggerFactory in PACKAGE_NAME (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class LoggerFactory {

    private static final DefaultLoggerFactory DEFAULT_LOGGER_FACTORY = new DefaultLoggerFactory();

    public static Logger getLogger(String name) {
        ILoggerFactory iLoggerFactory = getILoggerFactory();
        return iLoggerFactory.getLogger(name);
    }

    public static Logger getLogger(Class<?> aClass) {
        return getLogger(aClass.getName());
    }

    public static ILoggerFactory getILoggerFactory() {
        return DEFAULT_LOGGER_FACTORY;
    }

}
