package de.jns.core.io;

import de.jns.core.annotations.NotNull;

import java.io.Serializable;

public interface IHandler<I extends Serializable, O extends Serializable> {

    void in();

    void handle(I input);

    void out(@NotNull O out);

}