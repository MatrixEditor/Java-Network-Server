package certificate.keys;

import java.io.Serializable;

public interface SecureKey extends Serializable {

        byte getKey();

        String getFormat();
}
