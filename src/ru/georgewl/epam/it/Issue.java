package ru.georgewl.epam.it;

import ru.georgewl.epam.it.persistence.*;

/**
 * Represents issue
 * @author Yury Belozyorov, PTS
 */
@DBTable("ISSUE")
@DBSequence("ISSUE_SEQ")
public class Issue extends AbstractPersistable {
    
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
    
    @DBField("TOPIC")
    private String topic;
    
    public String getTopic() {
        return topic;
    }
    
    public void setTopic(String topic) {
        this.topic = topic;
    }
    
    @DBField("TYPE")
    private String type;
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    @DBField("PRIORITY")
    private int priority;
    
    public int getPriority() {
        return priority;
    }
    
    public void setPriority(int priority) {
        this.priority = priority;
    }
    
    @DBField("DESCRIPTION")
    private String description;
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public static final String ASSIGNEE_USER_ID="ASSIGNEE_USER_ID";
    
    @DBForeignKey(table= User.class, column= ASSIGNEE_USER_ID)
    private Reference assigneeRef;
    
    public Reference getAssigneeRef() {
        return assigneeRef;
    }
    
    public void setAssigneeRef(Reference assigneeRef) {
        this.assigneeRef = assigneeRef;
    }
    
    public static final String PROJECT_ID="PROJECT_ID";
    
    @DBForeignKey(table= Project.class, column= PROJECT_ID)
    private Reference projectRef;
    
    public Reference getProjectRef() {
        return projectRef;
    }
    
    public void setProjectRef(Reference projectRef) {
        this.projectRef = projectRef;
    }
    
}
