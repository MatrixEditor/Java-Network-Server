package de.jns.core.annotations;//@date 28.02.2021

import java.util.NoSuchElementException;

public enum CallbackType {
    NONE;


    public static boolean isCallback(Class<?> clazz) {
        if (clazz.getAnnotations().length != 0) {
            return clazz.getAnnotation(Callback.class) != null;
        } else return false;
    }

    public static Callback callbackType(Class<?> clazz) {
        if (isCallback(clazz)) {
            return clazz.getAnnotation(Callback.class);
        } else throw new NoSuchElementException();
    }
}
