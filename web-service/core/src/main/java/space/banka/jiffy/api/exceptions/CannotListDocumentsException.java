package space.banka.jiffy.api.exceptions;

public class CannotListDocumentsException extends RuntimeException {
    public CannotListDocumentsException(Throwable cause) {
        super("Cannot show a list of documents", cause);
    }
}
