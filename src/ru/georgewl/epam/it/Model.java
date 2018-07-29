package ru.georgewl.epam.it;

import ru.georgewl.epam.it.persistence.ObjectModel;
import ru.georgewl.epam.it.persistence.Persistable;


/**
 *
 * @author Yury Belozyorov, PTS
 */
public class Model extends ObjectModel {
    
    private static final Class<? extends Persistable>[] CLASSES= new Class[]{ User.class, Project.class, Issue.class };
    private static final String[] ALIASES= new String[]{ "user", "project", "issue" };

    @Override
    public Class<? extends Persistable>[] getClasses() {
        return CLASSES;
    }

    @Override
    protected String[] getAliases() {
       return ALIASES;
    }
    
}
