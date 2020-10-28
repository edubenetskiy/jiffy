package space.banka.jiffy.webservice;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "Jiffy Web Service",
                version = ""
        ),
        tags = {
                @Tag(name = "Documents", description = "Operations related to JSON documents."),
                @Tag(name = "Queries", description = "Operations related to queries."),
                @Tag(name = "Answers", description = "Operations related to queries’ answers."),
        }
)
public class JiffyWebServiceRestApplication extends Application {
}
