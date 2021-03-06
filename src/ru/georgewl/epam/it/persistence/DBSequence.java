package ru.georgewl.epam.it.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Links modeled object to its id sequence.<br/>
 * Parameter: sequence name
 * @author Yury Belozyorov, PTS
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBSequence {
    String value();
}
