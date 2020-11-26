package packet;


import packet.types.PacketType;
import packet.types.RemoteActionType;

/**
 * commhawk in de.commhawk.cloud
 * <p>
 * Class description...
 *
 * @author Leonard
 * @version ...
 * @date 17.10.2020
 **/
public class RemoteDesktopPacket extends Packet {

    private final RemoteActionType remoteActionType;

    public RemoteDesktopPacket(String source, String destination,
                               byte[] data, RemoteActionType remoteActionType) {
        super(source, destination, PacketType.REMOTE, data);
        this.remoteActionType = remoteActionType;
    }

    public RemoteActionType getRemoteActionType() {
        return remoteActionType;
    }

    public static RemoteDesktopPacket getNullValuePacket(RemoteActionType remoteActionType,
                                                         byte[] data) {
        return new RemoteDesktopPacket("", "", data, remoteActionType);
    }
}
