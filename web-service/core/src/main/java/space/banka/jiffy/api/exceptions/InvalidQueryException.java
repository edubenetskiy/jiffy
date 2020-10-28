package space.banka.jiffy.api.exceptions;

public class InvalidQueryException extends RuntimeException {

    public InvalidQueryException(String queryExpression, Throwable cause) {
        super("Invalid query expression: " + queryExpression, cause);
    }
}
