package de.jns.core.validation.security;


public interface SecureKey<T> {

    T getKey();

    String getFormat();
}
