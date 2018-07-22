package ru.georgewl.epam.it.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Yury Belozyorov, PTS
 */
class DBUtil {
    
    private static DBUtil instance= null;
    private static final String DBROOT= "D:/PROJECTS/tomcat_root/webapps/issueTracker/";
    
    
    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DBUtil getInstance() throws DBUtilInstanceException {
        if(instance==null){
            throw new DBUtilInstanceException("DBUtil is not instantiated. Use DBUtil.createInstance() first");
        }
        return instance;
    }
    
    public static void createInstance(String dbPath) throws SQLException, DBUtilInstanceException {
        if(instance!=null){
            throw new DBUtilInstanceException("DBUtil already instantiated. Use DBUtil.closeInstance() before");
        }
        Connection con= DriverManager.getConnection("jdbc:h2:"+DBROOT+dbPath, "sa", "");
        instance= new DBUtil(con);
    }
    
    public static void closeInstance() throws SQLException, DBUtilInstanceException {
        if(instance==null) {
            throw new DBUtilInstanceException("DBUtil have no instance. Use DBUtil.createInstance() to instantiate");
        }
        instance.con.close();
        instance= null;
    }
    
    private Connection con;
    
    private DBUtil(Connection connection) {
        con= connection;
    }
    
    public ResultSet executeQuery(String query) throws SQLException {
        PreparedStatement preparedStatement = con.prepareStatement(query);
        ResultSet rs = preparedStatement.executeQuery();
        //preparedStatement.close();
        return rs;
    }
    
    public int executeUpdate(String query) throws SQLException {
        int rs;
        try (PreparedStatement prepareStatement = con.prepareStatement(query)) {
            rs = prepareStatement.executeUpdate();
        }
        return rs;
    }
    
    public static String getSQLType(Class<?> clazz) throws DBUtilUnknownTypeException {
        String name= clazz.getName();
        
        if("java.lang.String".equals(name)) {
            return "varchar(255)";
        }
        else if("int".equals(name)) {
            return "int";
        }
        
        throw new DBUtilUnknownTypeException(clazz);
    }
    
}
