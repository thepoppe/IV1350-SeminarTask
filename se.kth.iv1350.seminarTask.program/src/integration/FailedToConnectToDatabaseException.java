package integration;

/**
 * An exception that is thrown if the databases are not available
 */
public class FailedToConnectToDatabaseException extends Exception {

    /**
     * Creates an instance of the class and stores the error message as an attribute
     * @param msg the error message to be stored
     */
    public FailedToConnectToDatabaseException(String msg) {
        super(msg);
    }
}
