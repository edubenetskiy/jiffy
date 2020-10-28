package space.banka.jiffy.impl;

import space.banka.jiffy.api.Document;
import space.banka.jiffy.api.exceptions.CannotOpenDocumentException;
import space.banka.jiffy.api.exceptions.CannotReadDocumentMetadataException;

import java.io.IOException;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.Instant;

public class FileDocument implements Document {

    private final Path filePath;

    public FileDocument(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getName() {
        return filePath.getFileName().toString();
    }

    @Override
    public SeekableByteChannel createByteChannelForReading() {
        try {
            return Files.newByteChannel(filePath, StandardOpenOption.READ);
        } catch (IOException exception) {
            throw new CannotOpenDocumentException(this, exception);
        }
    }

    @Override
    public long getSizeBytes() {
        try {
            return Files.size(filePath);
        } catch (IOException exception) {
            throw new CannotReadDocumentMetadataException(this, exception);
        }
    }

    @Override
    public Instant getLatestModificationTime() {
        try {
            return Files.getLastModifiedTime(filePath).toInstant();
        } catch (IOException exception) {
            throw new CannotReadDocumentMetadataException(this, exception);
        }
    }
}
