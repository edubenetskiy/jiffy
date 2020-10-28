package space.banka.jiffy.api.exceptions;

public class CannotCommitWriteableAnswerException extends RuntimeException {

    public CannotCommitWriteableAnswerException(Throwable cause) {
        super("An I/O error occurred on committing an answer", cause);
    }
}
