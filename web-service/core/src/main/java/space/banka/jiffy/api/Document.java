package space.banka.jiffy.api;

import space.banka.jiffy.api.exceptions.CannotOpenDocumentException;

import java.io.Serializable;
import java.nio.channels.SeekableByteChannel;
import java.time.Instant;

public interface Document extends Serializable {

    String getName();

    /**
     * @throws CannotOpenDocumentException if the document cannot be opened.
     */
    SeekableByteChannel createByteChannelForReading();

    long getSizeBytes();

    Instant getLatestModificationTime();
}
