package de.jns.defaultimpl;

import de.jns.*;

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

    private final WorkerGroup<DefaultMacro> workerGroup = new WorkerGroup<>();

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
            workerGroup.add(new DefaultMacro());
            workerGroup.add(new DefaultMacro());
            if (serverMode == ServerMode.ECHO) {
                socketServer.setMode(serverMode);
                startSocketServer();
            }
        } else LOGGER.log(Level.WARNING, "First set a ServerMode to start");


    }

    public void startConsoleHandler() {
        System.out.println("Commands: start, shutdown, list, endtimer\n");
        workerGroup.add(new DefaultMacro());
        workerGroup.get(CONSOLE).open().execAsync(() ->
                consoleHandler.waitForInput(this::consoleAction).loop(this::consoleAction));
    }

    /**
     * The SocketServer is started later when a command is typed
     * or...(?)
     */
    public void startSocketServer() {
        workerGroup.get(2).open().execAsync(() ->
                socketServer.start().loop()
        );
    }

    protected void shutDown() {
        socketServer.stop();
        workerGroup.workers()
                .stream()
                .parallel()
                .forEach(Macro::close);
    }

    /**
     * Here are the actions placed, that should be started when
     * typing a command.
     */
    public void consoleAction(String msg) {
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

    @Override
    public ServerType type() {
        return serverType;
    }

    @Override
    public int name() {
        return TAG;
    }

    @Override
    public void setMode(ServerMode mode) {
        serverMode = mode;
    }

}
