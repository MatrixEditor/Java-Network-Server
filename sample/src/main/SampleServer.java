package main;


import de.jns.defaultimpl.DefaultServer;
import de.jns.ServerMode;

/**
 * SampleServer in java.main (Java-Network-Server)
 * <p>
 * Echo-Server
 *
 * @version ...
 * @date 26.11.2020
 **/
public class SampleServer {

    public static void main(String[] args) {

        DefaultServer defaultServer = new DefaultServer(3000);
        defaultServer.setMode(ServerMode.ECHO);
        defaultServer.startConsoleHandler();
        //Echo-server
    }

}
