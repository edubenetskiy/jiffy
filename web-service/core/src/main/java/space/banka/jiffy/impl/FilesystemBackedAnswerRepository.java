package space.banka.jiffy.impl;

import space.banka.jiffy.api.Answer;
import space.banka.jiffy.api.AnswerRepository;
import space.banka.jiffy.api.WriteableAnswer;
import space.banka.jiffy.api.exceptions.CannotCommitWriteableAnswerException;
import space.banka.jiffy.api.exceptions.CannotCreateWriteableAnswerException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class FilesystemBackedAnswerRepository implements AnswerRepository {

    private final Path baseDirectory;

    public FilesystemBackedAnswerRepository(Path baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    @Override
    public boolean hasAnswerById(String id) {
        return getFile(id).exists();
    }

    @Override
    public Answer findAnswerById(String id) {
        return new FileAnswer(getFile(id));
    }

    @Override
    public WriteableAnswer create() {
        try {
            File file = File.createTempFile("answer", ".json.tmp", baseDirectory.toFile());
            return new FileWriteableAnswer(file);
        } catch (IOException exception) {
            throw new CannotCreateWriteableAnswerException(exception);
        }
    }

    @Override
    public Answer commit(WriteableAnswer answer) {
        if (!(answer instanceof FileWriteableAnswer)) {
            throw new UnsupportedOperationException(FilesystemBackedAnswerRepository.class + " supports only " + FileWriteableAnswer.class);
        }
        FileWriteableAnswer temporaryFileAnswer = (FileWriteableAnswer) answer;
        String id = UUID.randomUUID() + ".json";
        File targetFile = getFile(id);
        try {
            Files.move(temporaryFileAnswer.getFile().toPath(), targetFile.toPath());
            return new FileAnswer(targetFile);
        } catch (IOException exception) {
            throw new CannotCommitWriteableAnswerException(exception);
        }
    }

    private File getFile(String id) {
        return baseDirectory.resolve(id).toFile();
    }
}
