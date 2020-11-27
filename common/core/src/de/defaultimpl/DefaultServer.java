package de.defaultimpl;

import de.*;
import de.server.Server;
import de.server.ServerMode;
import de.server.ServerType;

import java.util.logging.Level;

/**
 * DefaultServer in de.jns (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class DefaultServer extends Server {

    private final DefaultSocketServer socketServer;

    private static final int CONSOLE = 0;

    /**
     * @param port - port to listen on
     * 
     */
    public DefaultServer(int port) {
        this(port, ServerType.WORKER);
    }

    /**  
     * @param port - port to listen on
     * @param type - Server type {@linkplain ServerType}
     */
    public DefaultServer(int port, ServerType type) {
        this(null, port, type);
    }

    /**
     * @param port - port to listen on
     * @param type - Server type {@linkplain ServerType}
     */
    public DefaultServer(ServerMode nullable, int port, ServerType type) {
        super();
        this.socketServer = new DefaultSocketServer(port);
        this.serverType = type;
        serverMode = nullable;
    }

    /**
     * Here the Server is started. The socketServer is activated,
     * but not started. In parallel the {@code ConsoleHandler}
     * waits for input form the console and loops afterwards.
     */
    protected void power_init() {
        if (serverMode != null && serverMode != ServerMode.NONE) {
            workerGroup.add(new DefaultAsyncRunner());
            workerGroup.add(new DefaultAsyncRunner());
            if (serverMode == ServerMode.ECHO) {
                startSocketServer();
            }
        } else LOGGER.log(Level.WARNING, "First set a ServerMode to start");


    }

    public void startConsoleHandler() {
        System.out.println("Commands: start, shutdown, list, endtimer\n");
        workerGroup.add(new DefaultAsyncRunner());
        addAction();
        workerGroup.get(CONSOLE).open().execAsync(consoleHandler::inputAndLoop);
    }

    /**
     * The SocketServer is started later when a command is typed
     * or...(?)
     */
    public void startSocketServer() {
        workerGroup.get(2).open().execAsync(() ->
                socketServer.run().loop()
        );
    }

    protected void shutDown() {
        socketServer.stop();
        workerGroup.workers()
                .stream()
                .parallel()
                .forEach(AsyncRunner::close);
    }

    /**
     * Here are the actions placed, that should be started when
     * typing a command.
     */
    private void addAction() {
        consoleHandler.addAction("default", this::consoleAction);
        consoleHandler.setListener("default");
    }

    private void consoleAction(String msg) {
        switch (msg) {
            case "shutdown":
                shutDown();
                LOGGER.log(Level.INFO, "LoadBalancer " +
                        "shut down! (time=" + System.currentTimeMillis() + ")");
                System.exit(0);
                break;
            case "list":
                String builder = getClass().getSimpleName() + socketServer.toString() + "\n|\n" +
                        workerGroup.toString();
                System.out.println(builder);
                break;
            case "endtimer":
                LOGGER.log(Level.INFO,"Timer stopped");
                break;
            case "start":
                power_init();
                LOGGER.log(Level.INFO, "Server started");
                break;
        }
    }

    public void setActionCommand(String cmd) {
        consoleHandler.setListener(cmd);
    }

}
