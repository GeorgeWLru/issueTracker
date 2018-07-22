package ru.georgewl.epam.it.persistence;

/**
 *
 * @author Yury Belozyorov, PTS
 */
public class PersistenceException extends Exception {

    /**
     * Creates a new instance of <code>PersistenceException</code> without
     * detail message.
     */
    public PersistenceException() {
    }

    /**
     * Constructs an instance of <code>PersistenceException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public PersistenceException(String msg) {
        super(msg);
    }
}
