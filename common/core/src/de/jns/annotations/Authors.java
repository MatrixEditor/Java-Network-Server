package de.jns.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Java-Network-Server in de.jns.annotations
 * <p>
 * Class description...
 *
 * @author MatrixEditor
 * @version ...
 * @date 26.11.2020
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Authors {
    String[] value() default {"MatrixEditor"};
}
