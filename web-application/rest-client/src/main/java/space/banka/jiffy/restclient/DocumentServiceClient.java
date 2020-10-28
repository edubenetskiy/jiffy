package space.banka.jiffy.restclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import space.banka.jiffy.webapi.DocumentService;

import javax.ws.rs.Path;

@RegisterRestClient
@Path("documents")
public interface DocumentServiceClient extends DocumentService {
}
