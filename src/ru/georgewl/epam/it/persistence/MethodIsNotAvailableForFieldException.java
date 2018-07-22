package ru.georgewl.epam.it.persistence;

import java.lang.reflect.Field;

/**
 *
 * @author Yury Belozyorov, PTS
 */
public class MethodIsNotAvailableForFieldException extends Exception {
    
    private String fieldName= null;
    private String methodName= null;
    
    public MethodIsNotAvailableForFieldException(Field field) {
        fieldName= field.getName();
        methodName= ReflectHelper.makeGetterName(fieldName);
    }

    @Override
    public String getMessage() {
        return "Method "+methodName+"() is not defined or defined inproperly for field "+fieldName;
    }

    
}
