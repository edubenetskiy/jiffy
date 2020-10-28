package space.banka.jiffy.impl;

import space.banka.jiffy.api.WriteableAnswer;

import java.io.*;

public class FileWriteableAnswer implements WriteableAnswer {

    private final File temporaryFile;

    public FileWriteableAnswer(File temporaryFile) {
        this.temporaryFile = temporaryFile;
    }

    public File getFile() {
        return temporaryFile;
    }

    @Override
    public OutputStream newOutputStream() throws IOException {
        return new BufferedOutputStream(new FileOutputStream(temporaryFile));
    }
}
