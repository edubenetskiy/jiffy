package space.banka.jiffy.api.exceptions;

public class DocumentNotFoundException extends RuntimeException {

    public DocumentNotFoundException(String documentName) {
        super("A requested document with name \"" + documentName + "\" does not exist");
    }
}
