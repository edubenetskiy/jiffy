package space.banka.jiffy.webapi;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("answers")
public interface AnswerService {

    @GET
    @Path("{id}")
    @Operation(summary = "Download an answer by its ID.")
    @Tag(ref = "Answers")
    @Produces(MediaType.APPLICATION_JSON)
    Response downloadAnswerById(@PathParam("id") String id);
}
