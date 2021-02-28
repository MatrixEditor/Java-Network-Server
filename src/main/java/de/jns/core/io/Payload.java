package de.jns.core.io;

import de.jns.core.annotations.Container;
import de.jns.core.annotations.NotNull;

import java.io.Serializable;

@Container
public interface Payload<V> extends Serializable {

    @NotNull V mappedData();

}
