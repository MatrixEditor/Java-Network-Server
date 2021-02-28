package de.jns.core.annotations;//@date 28.02.2021

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE, ElementType.TYPE_USE})
public @interface NotNull {

    String value() default "";

    Class<? extends Exception> exception() default Exception.class;
}
