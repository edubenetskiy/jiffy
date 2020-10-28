package space.banka.jiffy.impl;

import space.banka.jiffy.api.Document;
import space.banka.jiffy.api.DocumentRepository;
import space.banka.jiffy.api.exceptions.CannotListDocumentsException;
import space.banka.jiffy.api.exceptions.DocumentNotFoundException;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FilesystemBackedDocumentRepository implements DocumentRepository {

    public static final String JSON_FILENAMES_PATTERN = "*.json";
    private final Path baseDirectory;

    public FilesystemBackedDocumentRepository(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    @Override
    public Collection<Document> findAllDocuments() {
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(baseDirectory, JSON_FILENAMES_PATTERN)) {
            List<Document> documents = new ArrayList<>();
            for (Path jsonFile : directoryStream) {
                documents.add(new FileDocument(jsonFile));
            }
            return documents;
        } catch (IOException e) {
            throw new CannotListDocumentsException(e);
        }
    }

    @Override
    public Document findDocumentByName(String documentName) {
        Path filePath = baseDirectory.resolve(documentName);
        if (Files.notExists(filePath)) {
            throw new DocumentNotFoundException(documentName);
        }
        return new FileDocument(filePath);
    }
}
