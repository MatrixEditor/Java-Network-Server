package certificate.keys;

import java.io.Serializable;


public class DataKey implements Serializable {

    final byte[] key;

    public DataKey(byte[] Key) {
        this.key = Key;
    }

    public byte[] getKey() {
        return key;
    }
}
