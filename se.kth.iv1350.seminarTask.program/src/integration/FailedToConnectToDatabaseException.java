package integration;

public class FailedToConnectToDatabaseException extends Exception {
    public FailedToConnectToDatabaseException(String msg) {
        super(msg);
    }
}
