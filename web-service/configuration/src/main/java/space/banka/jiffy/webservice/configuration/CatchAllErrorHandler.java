package space.banka.jiffy.webservice.configuration;

import lombok.extern.slf4j.Slf4j;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Slf4j
public class CatchAllErrorHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        log.error("An unexpected exception caught", exception);
        return Response.serverError()
                .type(MediaType.TEXT_PLAIN_TYPE)
                .entity(exception.getLocalizedMessage())
                .build();
    }
}
