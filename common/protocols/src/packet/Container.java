package packet;

import java.io.Serializable;

@FunctionalInterface
public interface Container extends Serializable {

    byte[] getData();
}
