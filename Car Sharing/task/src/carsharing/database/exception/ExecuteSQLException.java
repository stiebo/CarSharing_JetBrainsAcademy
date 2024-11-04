package carsharing.database.exception;

public class ExecuteSQLException extends RuntimeException {
    public ExecuteSQLException(Exception e) {
        e.printStackTrace();
    }
}
