package main;

import de.jns.*;
import de.jns.monitoring.LoggerFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * SampleServer in java.main (Java-Network-Server)
 * <p>
 * Class description...
 *
 * @author
 * @version ...
 * @date 26.11.2020
 **/
public class SampleServer extends Server {

    private final WorkerGroup<DefaultMacro> workerGroup = new WorkerGroup<>();

    private final DefaultSocketServer socketServer;

    private static final int CONSOLE = 0, DATABASE = 1;

    private final ServerType serverType;

    private static final Logger LOGGER = LoggerFactory.getLogger(SampleServer.class);

    public static void main(String[] args) {
        System.out.println("Commands: start, shutdown, list, endtimer\n");
        new SampleServer(null, 23500,ServerType.NETWORK).startConsoleHandler();
    }

    /**
     * @param port - port to listen on
     * @param type - Server type {@linkplain ServerType}
     */
    private SampleServer(Object nullable, int port, ServerType type) {
        super();
        this.socketServer = new DefaultSocketServer(port);
        this.serverType = type;
    }

    /**
     * Here the LoadBalancer is started. The socketServer is activated,
     * but not started. In parallel the {@code ConsoleHandler}
     * waits for input form the console and loops afterwards.
     */
    protected void power() {
        workerGroup.add(new DefaultMacro());
        workerGroup.add(new DefaultMacro());
        startSocketServer();
    }

    public void startConsoleHandler() {
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
                power();
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

}
