package space.banka.jiffy.webservice.configuration;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import space.banka.jiffy.api.AnswerRepository;
import space.banka.jiffy.impl.FilesystemBackedAnswerRepository;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import java.nio.file.Files;
import java.nio.file.Path;

@Singleton
public class AnswerRepositoryConfiguration {

    @ConfigProperty(name = "jiffy.answers.baseDirectory")
    Path baseDirectory;

    @Produces
    AnswerRepository produceAnswerRepository() {
        if (Files.notExists(baseDirectory)) {
            throw new RuntimeException("The configured base directory \"" + baseDirectory + "\" " +
                                       "for an answer repository does not exists");
        }
        return new FilesystemBackedAnswerRepository(baseDirectory);
    }
}
