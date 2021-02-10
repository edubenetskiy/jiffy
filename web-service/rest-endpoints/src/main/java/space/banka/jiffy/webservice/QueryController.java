package space.banka.jiffy.webservice;

import lombok.extern.slf4j.Slf4j;
import space.banka.jiffy.api.*;
import space.banka.jiffy.api.exceptions.DocumentNotFoundException;
import space.banka.jiffy.api.exceptions.InvalidQueryException;
import space.banka.jiffy.webapi.QueryService;
import space.banka.jiffy.webapi.dto.QueryRequest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("queries")
@RequestScoped
@Slf4j
public class QueryController implements QueryService {

    @Inject
    DocumentRepository documentRepository;

    @Inject
    QueryFactory queryFactory;

    @Context
    UriInfo uriInfo;

    @Override
    public Response executeQuery(QueryRequest request) {
        try {
            Document document = documentRepository.findDocumentByName(request.getDocumentTitle());
            Query query = queryFactory.createQuery(request.getQueryExpression());
            Answer answer = query.execute(document);
            URI answerUri = uriInfo.getBaseUriBuilder().path("answers").path(answer.getID()).build();
            return Response.created(answerUri).build();
        } catch (DocumentNotFoundException | InvalidQueryException exception) {
            log.error("Failed to execute query", exception);
            return Response.status(Response.Status.BAD_REQUEST)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .entity(exception.getMessage())
                    .build();
        }
    }
}
