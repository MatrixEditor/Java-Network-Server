package de.jns.monitoring;

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

    public static ConsoleHandler getConsoleHandler(InputStream scanner) {
        if (scanner != null) {
            return new ConsoleHandler(new Scanner(scanner));
        } else return null;
    }

    public static ConsoleHandler getConsoleHandler(Scanner scanner) {
        if (scanner != null) {
            return new ConsoleHandler(scanner);
        } else return null;
    }

}
