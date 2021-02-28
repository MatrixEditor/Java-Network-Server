package de.jns.core.annotations;//@date 28.02.2021

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Callback {

    CallbackType type() default CallbackType.NONE;

    int cause() default -1;

}
