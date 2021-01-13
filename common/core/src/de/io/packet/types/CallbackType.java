package de.io.packet.types;

public enum CallbackType {

    SUCCESS(0),
    ERROR(1),
    PENDING(2),
    NONE(3);

    private final int label;

    CallbackType(int i) {
        this.label = i;
    }

    public int getCallbackCode() {
        return label;
    }
}
