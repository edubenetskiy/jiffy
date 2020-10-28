package space.banka.jiffy.webservice;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import space.banka.jiffy.api.DocumentRepository;
import space.banka.jiffy.webapi.DocumentService;
import space.banka.jiffy.webapi.dto.Document;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.stream.Collectors;

@Path("/documents")
@Produces(MediaType.APPLICATION_JSON)
public class DocumentController implements DocumentService {

    private DocumentRepository documentRepository;

    @Override
    @GET
    @Operation(summary = "List all documents.")
    @Tag(ref = "Documents")
    public Collection<Document> getAllDocuments() {
        return documentRepository.findAllDocuments().stream()
                .map(document -> Document.builder()
                        .name(document.getName())
                        .sizeBytes(document.getSizeBytes())
                        .latestModificationTime(document.getLatestModificationTime())
                        .build())
                .collect(Collectors.toList());
    }

    @Inject
    public void setDocumentRepository(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }
}
