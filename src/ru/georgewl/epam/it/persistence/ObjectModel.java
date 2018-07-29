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
    private final HashMap<String, Class<? extends Persistable>> aliases;
    
    /**
     * Should return array of modeled classes in order of dependence: depended class have to follow class it depends on
     * @return
     */
    public abstract Class<? extends Persistable>[] getClasses();
    
    protected abstract String[] getAliases();
    
    public ObjectModel() {
        List<Class<? extends Persistable>> list= Arrays.asList(getClasses());
        String[] alis= getAliases();
        classesEnvelope= new HashMap<>();
        aliases= new HashMap<>();
        for(Class<? extends Persistable> c : list) {
            ModelClassEnvelope mce= new ModelClassEnvelope(c);
            classesEnvelope.put(c, mce);
            int i= list.indexOf(c);
            aliases.put(alis[i], c);
        }
    }

    ModelClassEnvelope getClassEnvelope(Class<? extends Persistable> clazz) {
        return classesEnvelope.get(clazz);
    }
    
    public Class<? extends Persistable> getClassByAlias(String alias) {
        return aliases.get(alias);
    }
    
}
