package space.banka.jiffy.restclient;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import space.banka.jiffy.webapi.QueryService;

import javax.ws.rs.Path;

@RegisterRestClient
@Path("queries")
public interface QueryServiceClient extends QueryService {
}
