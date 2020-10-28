package space.banka.jiffy.api.exceptions;

import space.banka.jiffy.api.Document;

public class CannotOpenDocumentException extends RuntimeException {

    public CannotOpenDocumentException(Document document, Throwable cause) {
        super("An error occurred when opening the document “" + document.getName() + "”", cause);
    }
}
