package ru.georgewl.epam.it.persistence;

/**
 *
 * @author Yury Belozyorov, PTS
 */
public class DBUtilInstanceException extends Exception {

    /**
     * Creates a new instance of <code>DBUtilInstanceException</code> without
     * detail message.
     */
    public DBUtilInstanceException() {
    }

    /**
     * Constructs an instance of <code>DBUtilInstanceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public DBUtilInstanceException(String msg) {
        super(msg);
    }
}
