package de.io.packet;

import de.api.network.io.Address;
import de.api.network.packet.submission.Payload;
import de.api.network.packet.types.PacketType;

public interface Packet extends Payload {

    PacketType getProtocol();

    Address getAddress();
}
