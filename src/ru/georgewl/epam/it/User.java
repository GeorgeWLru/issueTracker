package ru.georgewl.epam.it;

import ru.georgewl.epam.it.persistence.*;

/**
 * Represents user
 * @author Yury Belozyorov, PTS
 */
@DBTable("USER")
@DBSequence("USER_SEQ")
public class User extends AbstractPersistable {
    
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

    @DBField("USERNAME")
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    @DBField("FIRSTNAME")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @DBField("LASTNAME")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
