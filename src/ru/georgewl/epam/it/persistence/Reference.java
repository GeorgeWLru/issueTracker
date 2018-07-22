package ru.georgewl.epam.it.persistence;

/**
 *
 * @author Yury Belozyorov, PTS
 */
public class Reference {
    private long id;
    private Class<? extends Persistable> clazz;
    
    public Reference(Persistable persistable) {
        id= persistable.getId();
        clazz= persistable.getClass();
    }

    public Reference(long id, Class<? extends Persistable> clazz) {
        this.id= id;
        this.clazz= clazz;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Class<? extends Persistable> getClazz() {
        return clazz;
    }

    public void setClazz(Class<? extends Persistable> clazz) {
        this.clazz = clazz;
    }
    
    
}
