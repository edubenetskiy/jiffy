package space.banka.jiffy.api.exceptions;

public class QueryExecutionException extends RuntimeException {

    public QueryExecutionException(Throwable cause) {
        super("Failed to execute a query", cause);
    }
}
