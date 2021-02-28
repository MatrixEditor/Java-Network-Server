package de.jns.core.validation.security;

import java.io.Serializable;
import java.util.Random;

public class DataKey implements Serializable, SecureKey<byte[]> {

    final byte[] key;

    public DataKey(byte[] Key) {
        this.key = Key;
    }

    public static DataKey throwNewKey(int depth) {
        Random random = new Random();
        byte[] k = new byte[depth];

        random.nextBytes(k);//random numbers
        return new DataKey(k);
    }

    public byte[] getKey() {
        return key;
    }

    @Override
    public String getFormat() {
        return "datakey.pq";
    }
}
