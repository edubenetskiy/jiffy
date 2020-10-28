package space.banka.jiffy.api.exceptions;

import space.banka.jiffy.api.Document;

public class CannotReadDocumentMetadataException extends RuntimeException {

    public CannotReadDocumentMetadataException(Document document, Throwable cause) {
        super("An error occurred when reading metadata for document " + document.getName(), cause);
    }
}
