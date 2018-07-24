package ru.georgewl.epam.it.persistence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Collector of modeled object.<br>
 * Its used by PersistenceHelper to start database session.
 * @author Yury Belozyorov, PTS
 */
public abstract class ObjectModel {
    
    private final HashMap<Class<? extends Persistable>, ModelClassEnvelope> classesEnvelope;
    
    /**
     * Should return array of modeled classes in order of dependence: depended class have to follow class it depends on
     * @return
     */
    public abstract Class<? extends Persistable>[] getClasses();
    
    public ObjectModel() {
        List<Class<? extends Persistable>> list= Arrays.asList(getClasses());
        classesEnvelope= new HashMap<>();
        for(Class<? extends Persistable> c : list) {
            ModelClassEnvelope mce= new ModelClassEnvelope(c);
            classesEnvelope.put(c, mce);
        }
    }

    ModelClassEnvelope getClassEnvelope(Class<? extends Persistable> clazz) {
        return classesEnvelope.get(clazz);
    }
    
}
