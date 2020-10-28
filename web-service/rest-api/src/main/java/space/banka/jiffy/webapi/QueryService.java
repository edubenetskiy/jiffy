package space.banka.jiffy.webapi;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.headers.Header;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import space.banka.jiffy.webapi.dto.QueryRequest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("queries")
@Tag(ref = "Queries")
public interface QueryService {

    @POST
    @Path("execute")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Executes a query.")
    @Tag(ref = "Queries")
    @APIResponse(
            responseCode = "201",
            description = "An answer is ready",
            headers = @Header(name = HttpHeaders.LOCATION, description = "A link to the answer."))
    @APIResponse(
            responseCode = "400",
            description = "Bad request")
    Response executeQuery(@RequestBody QueryRequest request);
}
