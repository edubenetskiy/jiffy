package space.banka.jiffy.webapi;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import space.banka.jiffy.webapi.dto.Document;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Tag(ref = "Documents")
public interface DocumentService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "List all documents.")
    Collection<Document> getAllDocuments();
}
