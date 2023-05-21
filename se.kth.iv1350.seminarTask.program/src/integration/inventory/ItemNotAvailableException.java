package integration.inventory;


/**
 * An exception that is thrown if the requested item cannot be collected
 */
public class ItemNotAvailableException extends Exception {
    public ItemNotAvailableException(String message) {
        super(message);
    }
}
