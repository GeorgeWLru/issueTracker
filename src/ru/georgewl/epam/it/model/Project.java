package ru.georgewl.epam.it.model;

import ru.georgewl.epam.it.persistence.*;

/**
 * Represents project
 * @author Yury Belozyorov, PTS
 */
@DBTable("PROJECT")
@DBSequence("PROJECT_SEQ")
public class Project extends AbstractPersistable {
    
    @DBId
    private long id;
    
    @Override
    public long getId() {
        return id;
    }
    
    @Override
    public void setId(long id) {
        this.id = id;
    }
    
    @DBField("NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
