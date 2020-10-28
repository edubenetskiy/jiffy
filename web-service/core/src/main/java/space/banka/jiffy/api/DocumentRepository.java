package space.banka.jiffy.api;

import java.util.Collection;

public interface DocumentRepository {

    Collection<Document> findAllDocuments();

    /**
     * @throws space.banka.jiffy.api.exceptions.DocumentNotFoundException if no documents with the specified name was found.
     */
    Document findDocumentByName(String documentName);
}
