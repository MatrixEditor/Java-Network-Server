package de.monitoring;

import de.io.Stream;

import java.util.Arrays;
import java.util.HashMap;
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

    private final HashMap<String, ConsoleAction> actionMap = new HashMap<>();

    private String msg, cmd;

    private boolean open = false;

    private Scanner scanner;

    public ConsoleHandler(Scanner scanner,
                          ConsoleAction action, String defaultCommand) {
        this.scanner = scanner;
        msg = "";
        addAction(defaultCommand, action);
    }

    public ConsoleHandler input() {
        if (scanner != null) {
            msg = scanner.nextLine();
            open = true;
            return this;
        } else LOGGER.log(Level.INFO, "Scanner is null");
        return null;
    }

    public ConsoleHandler inputAndRun() {
        if (scanner != null) {
            msg = scanner.nextLine();
            handle(msg);
        } else LOGGER.log(Level.WARNING, "Scanner is null");
        return this;
    }

    public void inputAndLoop() {
        do {
            inputAndRun();
        } while (open);
    }

    private void handle(String ctx) {
        if (ctx.equals("exit()")) {
            setNull();
        } else {
            actionMap.forEach((s, a) -> {
                if (s.equals(cmd)) {
                    a.onAction(ctx);
                }
            });
        }
    }

    public void addAction(String command, ConsoleAction action) {
        if (command != null && action != null) {
            if (!actionMap.containsKey(command)) actionMap.put(command, action);
        }
    }

    public void setListener(String cmd) {
        actionMap.forEach((s, a) -> {
            if (s.equals(cmd)){
                this.cmd = s;
            }
        });
    }

    public void setNull() {
        this.cmd = "";
    }

    public String getCommand() {
        return msg;
    }

    public void switchScanner(Scanner scanner) {
        this.scanner = scanner;
    }

}
