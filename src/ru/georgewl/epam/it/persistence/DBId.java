package ru.georgewl.epam.it.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks modeled object field as table id.<br/>
 * Parameter: column name
 * @author Yury Belozyorov, PTS
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBId {
    String value() default "ID";
}
