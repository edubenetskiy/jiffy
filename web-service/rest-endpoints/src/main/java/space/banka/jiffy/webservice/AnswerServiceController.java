package space.banka.jiffy.webservice;

import space.banka.jiffy.api.Answer;
import space.banka.jiffy.api.AnswerRepository;
import space.banka.jiffy.webapi.AnswerService;

import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.InputStream;

@Path("answers")
public class AnswerServiceController implements AnswerService {

    private AnswerRepository answerRepository;

    @Override
    public Response downloadAnswerById(final String id) {
        if (!answerRepository.hasAnswerById(id)) {
            return Response.status(Response.Status.NOT_FOUND)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .entity("There is no answer with ID " + id)
                    .build();
        }

        Answer answer = answerRepository.findAnswerById(id);
        return Response
                .ok((StreamingOutput) output -> {
                    try (InputStream inputStream = answer.newInputStream()) {
                        inputStream.transferTo(output);
                        output.flush();
                    } catch (IOException exception) {
                        throw new RuntimeException("An error occurred on downloading an answer", exception);
                    }
                })
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + id)
                .header(HttpHeaders.CONTENT_LENGTH, answer.getBytesCount())
                .build();
    }

    @Inject
    public void setAnswerRepository(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }
}
