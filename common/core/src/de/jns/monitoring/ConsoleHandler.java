package de.jns.monitoring;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * commhawk in de.commhawk.cloud.server
 * <p>
 * A Handler to get console input and execute
 * a {@link Runnable} command or method
 *
 * @author Leonard, Jan
 * @version ...
 * @date 05.11.2020
 **/
public class ConsoleHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleHandler.class);

    private String msg;

    private boolean open = false;

    private Scanner scanner;

    public ConsoleHandler(Scanner scanner) {
        this.scanner = scanner;
        msg = "";
    }

    public ConsoleHandler waitForInput() {
        if (scanner != null) {
            msg = scanner.nextLine();
            open = true;
            return this;
        } else LOGGER.log(Level.INFO, "Scanner is null");
        return null;
    }

    public ConsoleHandler waitForInput(ConsoleAction runnable) {
        if (scanner != null) {
            msg = scanner.nextLine();
            runnable.handle(msg);
            open = true;
            return this;
        } else LOGGER.log(Level.INFO, "Scanner is null");
        return null;
    }

    public void stop() {
        if (scanner != null) {
            open = false;
            scanner.close();
        } else LOGGER.log(Level.INFO, "Scanner is null");
    }

    public void loop(ConsoleAction runnable) {
        while (open) {
            msg = scanner.nextLine();
            runnable.handle(msg);
        }
    }

    public void loop() {
        while (open) {
            msg = scanner.nextLine();
        }
    }

    public synchronized ConsoleHandler sync() {
        synchronized (ConsoleHandler.class) {
            return this;
        }
    }

    public String getCommand() {
        return msg;
    }

    public void switchScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}
