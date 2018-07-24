package ru.georgewl.epam.it.persistence;

import java.lang.reflect.Field;

/**
 * Common iterface of persistable objects
 * @author Yury Belozyorov, PTS
 */
public interface Persistable {
    
    /**
     * Gets id of persistable
     * @return id
     */
    public long getId();

    /**
     * Sets id of persistable
     * @param id
     */
    public void setId(long id);

    /**
     * Sets value for persistent field
     * @param field field to set
     * @param value value
     * @throws MethodIsNotAvailableForFieldException
     */
    public void setFieldValue(Field field, Object value) throws MethodIsNotAvailableForFieldException;

    /**
     * Gets value of persistent field
     * @param field
     * @return value of field
     * @throws MethodIsNotAvailableForFieldException
     */
    public String getFieldValue(Field field) throws MethodIsNotAvailableForFieldException;

    /**
     * Gets reference to other persistent object
     * @param field reference field
     * @return reference to persistent object
     * @throws MethodIsNotAvailableForFieldException
     */
    public Reference getReference(Field field) throws MethodIsNotAvailableForFieldException;

    /**
     * Sets reference to other persistent object
     * @param field reference field
     * @param value reference to persistent object
     * @throws MethodIsNotAvailableForFieldException
     */
    public void setReference(Field field, Reference value) throws MethodIsNotAvailableForFieldException;
    
}
