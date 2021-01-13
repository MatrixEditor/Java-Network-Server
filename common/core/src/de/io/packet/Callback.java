package de.io.packet;


import de.io.packet.types.CallbackType;

//TODO: change
public interface Callback {

    CallbackType getCallbackType();

    void onSuccess(Packet packetType);

    void onFailure(Packet packetType);

    void onNext(Packet packetType);
}
