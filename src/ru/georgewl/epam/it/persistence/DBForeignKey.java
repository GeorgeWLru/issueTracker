package ru.georgewl.epam.it.persistence;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks modeled object Reference field as key to other table row.<br/>
 * Parameters: table - other table name, column - key column name.
 * @author Yury Belozyorov, PTS
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBForeignKey {
    Class<? extends Persistable> table();
    String column();
}
