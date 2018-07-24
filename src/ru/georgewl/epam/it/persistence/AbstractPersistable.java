package ru.georgewl.epam.it.persistence;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Realize common methods of Persistable. <br/>
 * You should implement this class to make exact modeled object.
 * @author Yury Belozyorov, PTS
 */
public abstract class AbstractPersistable implements Persistable {
    
    @Override
    public String getFieldValue(Field field) throws MethodIsNotAvailableForFieldException {
        String result= null;
        if(field!=null){
            String fieldName= field.getName();
            String getterName= ReflectHelper.makeGetterName(fieldName);
            Method m= null;
            try {
                m= this.getClass().getMethod(getterName);
                if(field.getType().equals(String.class)) {
                    result= (String)m.invoke(this);
                }
                if(field.getType().equals(int.class)) {
                    int i= (int)m.invoke(this);
                    result= Integer.toString(i);
                }
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                MethodIsNotAvailableForFieldException e= new MethodIsNotAvailableForFieldException(field);
                e.addSuppressed(ex);
                throw e;
            }
        }
        return result;
    }
    
    @Override
    public void setFieldValue(Field field, Object value) throws MethodIsNotAvailableForFieldException {
        if(field!=null){
            String fieldName= field.getName();
            String setterName= ReflectHelper.makeSetterName(fieldName);
            Method m= null;
            try {
                m= this.getClass().getMethod(setterName, field.getType());
                m.invoke(this, value);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                MethodIsNotAvailableForFieldException e= new MethodIsNotAvailableForFieldException(field);
                e.addSuppressed(ex);
                throw e;
            }
        }
    }
    
    @Override
    public Reference getReference(Field field) throws MethodIsNotAvailableForFieldException {
        Reference result= null;
        if(field!=null){
            String fieldName= field.getName();
            String getterName= ReflectHelper.makeGetterName(fieldName);
            Method m= null;
            try {
                m= this.getClass().getMethod(getterName);
                result= (Reference)m.invoke(this);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                MethodIsNotAvailableForFieldException e= new MethodIsNotAvailableForFieldException(field);
                e.addSuppressed(ex);
                throw e;
            }
        }
        return result;
    }
    
    @Override
    public void setReference(Field field, Reference value) throws MethodIsNotAvailableForFieldException {
        if(field!=null){
            String fieldName= field.getName();
            String setterName= ReflectHelper.makeSetterName(fieldName);
            Method m= null;
            try {
                m= this.getClass().getMethod(setterName, Reference.class);
                m.invoke(this, value);
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                MethodIsNotAvailableForFieldException e= new MethodIsNotAvailableForFieldException(field);
                e.addSuppressed(ex);
                throw e;
            }
        }
    }
    
}
