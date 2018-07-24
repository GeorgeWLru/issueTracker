/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.georgewl.epam.it.persistence;

/**
 * Raises on unimplemented database type
 * @author Yury Belozyorov, PTS
 */
public class DBUtilUnknownTypeException extends Exception {

    private Class<?> clazz;
    
    /**
     * Creates a new instance of <code>DBUtilUnknowType</code> without detail
     * message.
     * @param clazz
     */
    public DBUtilUnknownTypeException(Class<?> clazz) {
        this.clazz= clazz;
    }

    /**
     * Constructs an instance of <code>DBUtilUnknowType</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DBUtilUnknownTypeException(String msg) {
        super(msg);
    }
    
    @Override
    public String getMessage() {
        return "Java type "+clazz.getName()+" is not implemented";
    }

}
