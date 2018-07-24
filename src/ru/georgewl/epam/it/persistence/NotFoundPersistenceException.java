package ru.georgewl.epam.it.persistence;

/**
 * Raises if persistent object not found in store
 * @author user
 */
public class NotFoundPersistenceException extends PersistenceException {
    
    private final long id;
    private final Class clazz;
    
    public NotFoundPersistenceException(long id, Class clazz) {
        this.id= id;
        this.clazz= clazz;
    }
    
    @Override
    public String getMessage() {
        return "Object "+clazz.getName()+":"+id+" not found in store"; //To change body of generated methods, choose Tools | Templates.
    }

    
}
