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

    private static ILoggerFactory iLoggerFactory;

    private static int INITIAL_VALUE = 0;

    public static Logger getLogger(String name) {
        ILoggerFactory iLoggerFactory = getILoggerFactory();
        return iLoggerFactory.getLogger(name);
    }

    public static Logger getLogger(Class<?> aClass) {
        return getLogger(aClass.getName());
    }

    public static ILoggerFactory getILoggerFactory() {
        if (INITIAL_VALUE == 0) {
            createLoggerFactory();
        }

        switch (INITIAL_VALUE){
            case 1:
                return ILoggerFactory.newFactory();
            case 2:
                return iLoggerFactory;
            default:
                throw new ExceptionInInitializerError("Warning: Cannot initialize factory");
        }
    }

    private static void createLoggerFactory() {
        if (iLoggerFactory == null) {
            iLoggerFactory = ILoggerFactory.newFactory();
            INITIAL_VALUE = 2;
        } else INITIAL_VALUE = 1;
    }

}
