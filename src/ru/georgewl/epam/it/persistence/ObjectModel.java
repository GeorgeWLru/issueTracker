package ru.georgewl.epam.it.persistence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Yury Belozyorov, PTS
 */
public abstract class ObjectModel {
    
    private final HashMap<Class<? extends Persistable>, ModelClassEnvelope> classesEnvelope;
    
    public abstract Class<? extends Persistable>[] getClasses();
    
    public ObjectModel() {
        List<Class<? extends Persistable>> list= Arrays.asList(getClasses());
        classesEnvelope= new HashMap<>();
        for(Class<? extends Persistable> c : list) {
            ModelClassEnvelope mce= new ModelClassEnvelope(c);
            classesEnvelope.put(c, mce);
        }
    }

    public ModelClassEnvelope getClassEnvelope(Class<? extends Persistable> clazz) {
        return classesEnvelope.get(clazz);
    }
    
}
