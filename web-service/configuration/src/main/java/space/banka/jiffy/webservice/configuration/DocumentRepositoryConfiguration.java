package space.banka.jiffy.webservice.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import space.banka.jiffy.api.DocumentRepository;
import space.banka.jiffy.impl.FilesystemBackedDocumentRepository;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.nio.file.Files;
import java.nio.file.Path;

@Singleton
public class DocumentRepositoryConfiguration {

    @ConfigProperty(name = "jiffy.documents.baseDirectory")
    Path baseDirectory;

    @Produces
    DocumentRepository produceDocumentRepository() {
        if (Files.notExists(baseDirectory)) {
            throw new RuntimeException("The configured base directory \"" + baseDirectory + "\" " +
                                       "for a document repository does not exists");
        }
        return new FilesystemBackedDocumentRepository(baseDirectory);
    }
}
