package ru.georgewl.epam.it.persistence;


public class NotFoundPersistenceException extends PersistenceException {
    
    private long id;
    private Class clazz;
    
    public NotFoundPersistenceException(long id, Class clazz) {
        this.id= id;
        this.clazz= clazz;
    }
    
    @Override
    public String getMessage() {
        return "Object "+clazz.getName()+":"+id+" not found in store"; //To change body of generated methods, choose Tools | Templates.
    }

    
}
