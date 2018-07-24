package ru.georgewl.epam.it.persistence;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;

/**
 * Envelop under modeled class for accessing object persistence info thru annotations
 * @author Yury Belozyorov, PTS
 */
class ModelClassEnvelope {
    
    private Class<? extends Persistable> clazz;
    private String table;
    private String sequence;
    private String id;
    private HashMap<String, Field> fields= null;
    private HashMap<String, Field> refs= null;
    private HashMap<String, Class<? extends Persistable>> refClasses= null;

    
    public ModelClassEnvelope(Class<? extends Persistable> clazz) {
        this.clazz= clazz;
        
        DBTable dbt= clazz.getAnnotation(DBTable.class);
        table= dbt.value();
        
        DBSequence dbs= clazz.getAnnotation(DBSequence.class);
        sequence= dbs.value();
        
        fields= new HashMap<>();
        refs= new HashMap<>();
        refClasses= new HashMap<>();
        Field[] declaredFields= clazz.getDeclaredFields();
        for(Field f : declaredFields) {
            int modifs= f.getModifiers();
            if(Modifier.isPrivate(modifs)){
                DBField dbf= f.getAnnotation(DBField.class);
                DBId dbi= f.getAnnotation(DBId.class);
                DBForeignKey dbfk= f.getAnnotation(DBForeignKey.class);
                if(dbf!=null) {
                    fields.put(dbf.value(), f);
                }
                else if(dbfk!=null) {
                    refs.put(dbfk.column(), f);
                    refClasses.put(dbfk.column(), dbfk.table());
                }
                else if(dbi!=null) {
                    id= dbi.value();
                }
            }
        }
        
    }

    public Class<? extends Persistable> getClazz() {
        return clazz;
    }

    public String getTableName() {
        return table;
    }

    public String getSequenceName() {
        return sequence;
    }

    public String getIdFieldName() {
        return id;
    }

    public HashMap<String, Field> getFieldsMap() {
        return fields;
    }

    public HashMap<String, Field> getRefsMap() {
        return refs;
    }

    public HashMap<String, Class<? extends Persistable>> getRefClassesMap() {
        return refClasses;
    }

    
}
