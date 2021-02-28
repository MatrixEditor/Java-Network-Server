package de.jns.core.io.stream;//@date 28.02.2021

import de.jns.core.io.IHandler;
import de.jns.core.validation.Validator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class StreamReader<T extends Stream, I extends Serializable, O extends Serializable>
        implements IHandler<I, O> {

    protected final List<Validator> validators = new ArrayList<>();
    protected T stream;

    public StreamReader(T stream) {
        this.stream = stream;
    }

    public abstract I filter(I input, int currentStep);

    public abstract void open();

    public abstract I next();

    public abstract void close();

    public List<Validator> getValidators() {
        return validators;
    }

    public T getStream() {
        return stream;
    }


}
