package de.monitoring;

import java.io.InputStream;
import java.util.Scanner;

/**
 * ConsoleFactory in de.jns.monitoring (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class ConsoleFactory {

    public static ConsoleHandler getEmptyConsoleHandler(InputStream scanner) {
        if (scanner != null) {
            return getEmptyConsoleHandler(new Scanner(scanner));
        } else return null;
    }

    public static ConsoleHandler getEmptyConsoleHandler(Scanner scanner) {
        if (scanner != null) {
            return new ConsoleHandler(scanner, null, null);
        } else return null;
    }

}
