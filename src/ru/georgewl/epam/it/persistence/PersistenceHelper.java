package ru.georgewl.epam.it.persistence;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton accessor to persistence methods.<br/>
 * Use static method getInstance() to take access to methods.
 * @author Yury Belozyorov, PTS
 */
public class PersistenceHelper {
    
    private ObjectModel model;
    
    private PersistenceHelper() {
    }
    
    public static PersistenceHelper getInstance() {
        return PersistenceHelperHolder.INSTANCE;
    }
    
    private static class PersistenceHelperHolder {
        private static final PersistenceHelper INSTANCE = new PersistenceHelper();
    }
    
    /**
     * Starts database session
     * @param model object model to work with
     * @param dbPath path to database
     * @throws SQLException
     * @throws DBUtilInstanceException
     */
    public void start(ObjectModel model, String dbPath) throws SQLException, DBUtilInstanceException {
        this.model= model;
        DBUtil.createInstance(dbPath);
        ensureDB();
    }
    
    /**
     * Stops database session
     * @throws SQLException
     * @throws DBUtilInstanceException
     */
    public void stop() throws SQLException, DBUtilInstanceException {
        DBUtil.closeInstance();
    }
    
    private void ensureDBClass(Class<? extends Persistable> c) throws DBUtilInstanceException, SQLException, DBUtilUnknownTypeException {
        ModelClassEnvelope mce= model.getClassEnvelope(c);
        StringBuilder sb= new StringBuilder("create table if not exists ");
        sb.append(mce.getTableName());
        DBUtil.getInstance().executeUpdate(sb.toString());
        
        sb= new StringBuilder("alter table if exists ");
        sb.append(mce.getTableName());
        sb.append(" add column if not exists ");
        sb.append(mce.getIdFieldName());
        sb.append(" int not null primary key");
        DBUtil.getInstance().executeUpdate(sb.toString());
                
        sb= new StringBuilder("create sequence if not exists ");
        sb.append(mce.getSequenceName());
        DBUtil.getInstance().executeUpdate(sb.toString());
        
        for(String key : mce.getFieldsMap().keySet()) {
            Field f= mce.getFieldsMap().get(key);
            String type= DBUtil.getSQLType(f.getType());
            
            sb= new StringBuilder("alter table if exists ");
            sb.append(mce.getTableName());
            sb.append(" add column if not exists ");
            sb.append(key);
            sb.append(" ");
            sb.append(type);
            DBUtil.getInstance().executeUpdate(sb.toString());
        }
        
        for(String key : mce.getRefsMap().keySet()) {
            Class cl= mce.getRefClassesMap().get(key);
            String table= model.getClassEnvelope(cl).getTableName();
            String id= model.getClassEnvelope(cl).getIdFieldName();
            
            sb= new StringBuilder("alter table if exists ");
            sb.append(mce.getTableName());
            sb.append(" add column if not exists ");
            sb.append(key);
            sb.append(" int not null");
            DBUtil.getInstance().executeUpdate(sb.toString());
            
            sb= new StringBuilder("alter table if exists ");
            sb.append(mce.getTableName());
            sb.append(" add constraint if not exists ");
            sb.append("FK_").append(key);
            sb.append(" foreign key (").append(key).append(") references ");
            sb.append(table).append("(").append(id).append(")");
            
            DBUtil.getInstance().executeUpdate(sb.toString());
        }
        
    }
    
    private void ensureDB(){
        for(Class<? extends Persistable> c : model.getClasses()){
            try {
                ensureDBClass(c);
            } catch (DBUtilInstanceException | SQLException | DBUtilUnknownTypeException ex) {
                Logger.getLogger(PersistenceHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private <T extends Persistable> T newPersistable(ResultSet rs, Class<T> clazz, ModelClassEnvelope mce, long id) throws PersistenceException {
        Set<String> fieldKeys= mce.getFieldsMap().keySet();
        Set<String> refKeys= mce.getRefsMap().keySet();
        
        T pers= null;
        try {
            pers= clazz.newInstance();
            pers.setId(id);
            // obtain fields
            for(String key : fieldKeys) {
                Object o= rs.getObject(key);
                Field f= mce.getFieldsMap().get(key);
                pers.setFieldValue(f, o);
            }
            // obtain references
            for(String key : refKeys) {
                long o= rs.getLong(key);
                Field f= mce.getRefsMap().get(key);
                Class cl= mce.getRefClassesMap().get(key);
                Reference ref= new Reference(o, cl);
                pers.setReference(f, ref);
            }
        }
        catch (InstantiationException | IllegalAccessException | SQLException | MethodIsNotAvailableForFieldException ex) {
            PersistenceException e= new PersistenceException();
            e.addSuppressed(ex);
            throw e;
        }
        return pers;
    }
    
    /**
     * Finds persistable object in store by class and id and return it
     * @param <T> 
     * @param clazz Class of modeled object
     * @param id id of persistable object
     * @return persistable object
     * @throws PersistenceException on persistence errors
     * @throws DBUtilInstanceException on base errors
     */
    public <T extends Persistable> T retrieve(Class<T> clazz, long id) throws PersistenceException, DBUtilInstanceException {
        StringBuilder query= new StringBuilder("select ");
        ModelClassEnvelope mce= model.getClassEnvelope(clazz);
        Set<String> fieldKeys= mce.getFieldsMap().keySet();
        boolean firstField= true;
        for(String key : fieldKeys) {
            if(!firstField){
                query.append(", ");
            }
            else{
                firstField= false;
            }
            query.append(key);
        }
        Set<String> refKeys= mce.getRefsMap().keySet();
        for(String key : refKeys) {
            if(!firstField){
                query.append(", ");
            }
            else{
                firstField= false;
            }
            query.append(key);
        }
        
        query.append(" from ");
        query.append(mce.getTableName());
        query.append(" where ");
        query.append(mce.getIdFieldName()).append("=").append(id);
        
        T persistable= null;
        try {
            ResultSet rs= DBUtil.getInstance().executeQuery(query.toString());

            if(rs.next()) {
                persistable= newPersistable(rs, clazz, mce, id);
            }
        }
        catch (SQLException ex) {
            PersistenceException e= new PersistenceException();
            e.addSuppressed(ex);
            throw e;
        }
        
        if(persistable==null){
            throw new NotFoundPersistenceException(id, clazz);
        }
        return persistable;
    }
    
    /**
     * Changes persisted object in store
     * @param persistable persistable to change
     * @throws PersistenceException on persistence errors
     * @throws DBUtilInstanceException on base errors
     */
    public void update(Persistable persistable) throws PersistenceException, DBUtilInstanceException {
        try {
            ModelClassEnvelope mce= model.getClassEnvelope(persistable.getClass());
            StringBuilder query= new StringBuilder("update ");
            query.append(mce.getTableName());
            query.append(" set ");
            Set<String> keys= mce.getFieldsMap().keySet();
            boolean firstField= true;
            for(String key : keys) {
                if(!firstField){
                    query.append(", ");
                }
                else{
                    firstField= false;
                }
                query.append(key);
                query.append("=");
                Field f= mce.getFieldsMap().get(key);
                String value= persistable.getFieldValue(f);
                query.append("'").append(value).append("'");
            }
            keys= mce.getRefsMap().keySet();
            for(String key : keys) {
                if(!firstField){
                    query.append(", ");
                }
                else{
                    firstField= false;
                }
                query.append(key);
                query.append("=");
                Field f= mce.getRefsMap().get(key);
                Reference ref= persistable.getReference(f);
                String value= Long.toString(ref.getId());
                query.append(value);
            }
            query.append(" where ");
            query.append(mce.getIdFieldName()).append("=").append(persistable.getId());
        
            DBUtil.getInstance().executeUpdate(query.toString());
        }
        catch (SQLException | MethodIsNotAvailableForFieldException ex) {
            PersistenceException e= new PersistenceException();
            e.addSuppressed(ex);
            throw e;
        }
            
    }
    
    /**
     * Deletes persistable from store.
     * @param persistable persistable object
     * @throws PersistenceException on persistence errors
     * @throws DBUtilInstanceException on base errors
     */
    public void delete(Persistable persistable) throws PersistenceException, DBUtilInstanceException {
        try {
            ModelClassEnvelope mce= model.getClassEnvelope(persistable.getClass());
            StringBuilder query= new StringBuilder("delete from ");
            query.append(mce.getTableName());
            query.append(" where ");
            query.append(mce.getIdFieldName()).append("=").append(persistable.getId());

            DBUtil.getInstance().executeUpdate(query.toString());
        }
        catch (SQLException ex) {
            PersistenceException e= new PersistenceException(ex.getMessage());
            e.addSuppressed(ex);
            throw e;
        }
        
    }
    
    /**
     * Persists persistable to store (adds new row to table)
     * @param persistable object to place to store
     * @throws PersistenceException on persistence errors
     * @throws DBUtilInstanceException on base errors
     */
    public void persist(Persistable persistable) throws PersistenceException, DBUtilInstanceException {
        try {
            ModelClassEnvelope mce= model.getClassEnvelope(persistable.getClass());
            long id= 0;
            StringBuilder query= new StringBuilder("select ");
            query.append(mce.getSequenceName());
            query.append(".nextval");

            ResultSet rs= DBUtil.getInstance().executeQuery(query.toString());
            if(rs.next()) {
                id= rs.getLong(1);
            }

            query= new StringBuilder("insert into ");
            StringBuilder values= new StringBuilder();
            query.append(mce.getTableName());
            query.append(" (");
            query.append(mce.getIdFieldName());
            values.append(id);
            Set<String> keys= mce.getFieldsMap().keySet();
            for(String key : keys) {
                query.append(", ");
                values.append(", ");
                query.append(key);
                Field f= mce.getFieldsMap().get(key);
                String value= persistable.getFieldValue(f);
                values.append("'").append(value).append("'");
            }
            keys= mce.getRefsMap().keySet();
            for(String key : keys) {
                query.append(", ");
                values.append(", ");
                query.append(key);
                Field f= mce.getRefsMap().get(key);
                Reference ref= persistable.getReference(f);
                values.append(ref.getId());
            }
            query.append(") values (");
            query.append(values);
            query.append(")");

            DBUtil.getInstance().executeUpdate(query.toString());
            persistable.setId(id);
        }
        catch (SQLException | MethodIsNotAvailableForFieldException ex) {
            PersistenceException e= new PersistenceException();
            e.addSuppressed(ex);
            throw e;
        }
    }
    
    /**
     * Checks if persistable presents in store
     * @param persistable object to check
     * @return true if object presents in store, else false
     * @throws PersistenceException on persistence errors
     * @throws DBUtilInstanceException on base errors
     */
    public boolean isPersisted(Persistable persistable) throws DBUtilInstanceException, PersistenceException {
        boolean result= false;
        ModelClassEnvelope mce= model.getClassEnvelope(persistable.getClass());
        StringBuilder query= new StringBuilder("select * from ");
        query.append(mce.getTableName());
        query.append(" where ");
        query.append(mce.getIdFieldName()).append("=").append(persistable.getId());
        
        try {
            ResultSet rs= DBUtil.getInstance().executeQuery(query.toString());

            if(rs.next()) {
                result= true;
            }
        }
        catch (SQLException ex) {
            PersistenceException e= new PersistenceException();
            e.addSuppressed(ex);
            throw e;
        }
        
        return result;
    }
    
    /**
     * Updates persistable object if its already presents in store, else persists it
     * @param persistable persistable object
     * @throws PersistenceException on persistence errors
     * @throws DBUtilInstanceException on base errors
     */
    public void save(Persistable persistable) throws PersistenceException, DBUtilInstanceException {
        if(isPersisted(persistable)){
            update(persistable);
        }
        else {
            persist(persistable);
        }
    }
    
    /**
     * Retrieves all persistable object by class
     * @param <T>
     * @param clazz class of object to retrieve
     * @return list of persisted objects
     * @throws PersistenceException on persistence errors
     * @throws DBUtilInstanceException on base errors
     */
    public <T extends Persistable> List<T> selectAll(Class<T> clazz) throws PersistenceException, DBUtilInstanceException {
        List<T> result= new LinkedList<>();
        try {
            ModelClassEnvelope mce= model.getClassEnvelope(clazz);

            StringBuilder query= new StringBuilder("select ");
            query.append(mce.getIdFieldName());
            Set<String> keys= mce.getFieldsMap().keySet();
            keys.forEach((key) -> {
                query.append(", ").append(key);
            });
            Set<String> refKeys= mce.getRefsMap().keySet();
            refKeys.forEach((key) -> {
                query.append(", ").append(key);
            });
            query.append(" from ");
            query.append(mce.getTableName());

            ResultSet rs= DBUtil.getInstance().executeQuery(query.toString());
            while(rs.next()) {
                long id= rs.getLong(mce.getIdFieldName());
                T pers= newPersistable(rs, clazz, mce, id);
                result.add(pers);
            }
        }
        catch (SQLException ex) {
            PersistenceException e= new PersistenceException();
            e.addSuppressed(ex);
            throw e;
        }

        return result;
    }
    
    /**
     * Retrieves persistable objects by class that is filtered by reference (foreign key)
     * @param <T>
     * @param clazz class of objects to retrieve
     * @param column foreign key column name 
     * @param ref reference to persistable object to filter by
     * @return list of persisted objects
     * @throws PersistenceException on persistence errors
     * @throws DBUtilInstanceException on base errors
     */
    public <T extends Persistable> List<T> selectFilteredByReference(Class<T> clazz, String column, Reference ref) throws PersistenceException, DBUtilInstanceException {
        List<T> result= new LinkedList<>();
        try {
            ModelClassEnvelope mce= model.getClassEnvelope(clazz);

            StringBuilder query= new StringBuilder("select ");
            query.append(mce.getIdFieldName());
            Set<String> keys= mce.getFieldsMap().keySet();
            keys.forEach((key) -> {
                query.append(", ").append(key);
            });
            Set<String> refKeys= mce.getRefsMap().keySet();
            refKeys.forEach((key) -> {
                query.append(", ").append(key);
            });
            query.append(" from ");
            query.append(mce.getTableName());
            query.append(" where ");
            query.append(column).append("=").append(ref.getId());
            
            ResultSet rs= DBUtil.getInstance().executeQuery(query.toString());
            while(rs.next()) {
                long id= rs.getLong(mce.getIdFieldName());
                T pers= newPersistable(rs, clazz, mce, id);
                result.add(pers);
            }
        }
        catch (SQLException ex) {
            PersistenceException e= new PersistenceException();
            e.addSuppressed(ex);
            throw e;
        }

        return result;
    }
    
    /**
     * Creates new Reference by persistable object
     * @param persistable object to make reference to
     * @return reference to persistable object
     */
    public Reference makeReference(Persistable persistable) {
        return new Reference(persistable);
    }
    
    /**
     * Restore persistable by its reference
     * @param ref reference to persistable
     * @return persistable
     * @throws PersistenceException on persistence errors
     * @throws DBUtilInstanceException on base errors
     */
    public Persistable getPersistable(Reference ref) throws PersistenceException, DBUtilInstanceException {
        long id= ref.getId();
        Class<? extends Persistable> c= ref.getClazz();
        Persistable p= retrieve(c, id);
        return p;
    }
    
    public Class<? extends Persistable> getClassByAlias(String alias) {
        return model.getClassByAlias(alias);
    }
    
}
