package space.banka.jiffy.webapp.pages;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import space.banka.jiffy.restclient.DocumentServiceClient;
import space.banka.jiffy.restclient.QueryServiceClient;
import space.banka.jiffy.webapi.dto.Document;
import space.banka.jiffy.webapi.dto.QueryRequest;
import space.banka.jiffy.webapp.i18n.Messages;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import java.time.Duration;
import java.time.Instant;

@Named
@RequestScoped
@Slf4j
public class QueryBackingBean {

    @Inject
    Messages messages;

    @Inject
    @RestClient
    DocumentServiceClient documentClient;

    @Inject
    @RestClient
    QueryServiceClient queryClient;

    @Getter
    @Setter
    private String selectedDocumentName;

    @Getter
    @Setter
    private String queryExpression;

    @Getter
    @Setter
    private String answerUrl;

    public Iterable<Document> getAllDocuments() {
        return documentClient.getAllDocuments();
    }

    public String executeQuery() {
        Instant startTime = Instant.now();
        QueryRequest queryRequest = QueryRequest.builder()
                .documentTitle(selectedDocumentName)
                .queryExpression(queryExpression)
                .build();
        try {
            Response response = queryClient.executeQuery(queryRequest);
            if (response.getStatusInfo().toEnum() == Response.Status.CREATED) {
                answerUrl = response.getHeaderString(HttpHeaders.LOCATION);
            }
            Instant endTime = Instant.now();
            log.info("A query took " + Duration.between(startTime, endTime).toMillis() + " milliseconds");
        } catch (WebApplicationException exception) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, messages.get("messages.unexpectedErrorPleaseRetryAndReport"), null));
            log.error("Failed to perform a query", exception);
        }
        return "index";
    }
}
