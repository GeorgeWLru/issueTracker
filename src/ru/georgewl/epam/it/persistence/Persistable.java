package ru.georgewl.epam.it.persistence;

import java.lang.reflect.Field;

/**
 *
 * @author Yury Belozyorov, PTS
 */
public interface Persistable {
    
    public long getId();
    public void setId(long id);

    public void setFieldValue(Field field, Object value) throws MethodIsNotAvailableForFieldException;
    public String getFieldValue(Field field) throws MethodIsNotAvailableForFieldException;
    public Reference getReference(Field field) throws MethodIsNotAvailableForFieldException;
    public void setReference(Field field, Reference value) throws MethodIsNotAvailableForFieldException;
    
}
