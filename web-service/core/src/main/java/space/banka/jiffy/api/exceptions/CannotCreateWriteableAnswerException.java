package space.banka.jiffy.api.exceptions;

public class CannotCreateWriteableAnswerException extends RuntimeException {

    public CannotCreateWriteableAnswerException(Throwable cause) {
        super("Cannot create a writeable answer", cause);
    }
}
