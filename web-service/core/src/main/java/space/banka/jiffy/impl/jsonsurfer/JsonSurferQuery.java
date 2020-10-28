package space.banka.jiffy.impl.jsonsurfer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jsfr.json.JsonSurfer;
import org.jsfr.json.SurfingConfiguration;
import org.jsfr.json.path.JsonPath;
import space.banka.jiffy.api.*;
import space.banka.jiffy.api.exceptions.QueryExecutionException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;

@Slf4j
public class JsonSurferQuery implements Query {

    private final AnswerRepository answerRepository;
    private final JsonPath jsonPath;
    private final JsonSurfer jsonSurfer;

    public JsonSurferQuery(AnswerRepository answerRepository, JsonPath jsonPath, JsonSurfer jsonSurfer) {
        this.answerRepository = answerRepository;
        this.jsonPath = jsonPath;
        this.jsonSurfer = jsonSurfer;
    }

    @Override
    public Answer execute(Document document) {
        try {
            WriteableAnswer answer = answerRepository.create();
            OutputStream outputStream = answer.newOutputStream();
            JsonGenerator generator = new ObjectMapper().createGenerator(outputStream);
            generator.writeStartArray();
            SurfingConfiguration surfingConfiguration = jsonSurfer.configBuilder()
                    .bind(jsonPath, (value, context) -> {
                        try {
                            generator.writeObject(value);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    })
                    .build();
            InputStream documentInputStream = Channels.newInputStream(document.createByteChannelForReading());
            jsonSurfer.surf(documentInputStream, surfingConfiguration);
            generator.writeEndArray();
            generator.close();
            outputStream.flush();
            outputStream.close();
            return answerRepository.commit(answer);
        } catch (IOException e) {
            throw new QueryExecutionException(e);
        }
    }
}
