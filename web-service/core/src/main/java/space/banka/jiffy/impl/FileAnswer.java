package space.banka.jiffy.impl;

import space.banka.jiffy.api.Answer;

import java.io.*;

public class FileAnswer implements Answer {

    private final File file;

    public FileAnswer(File file) {
        this.file = file;
    }

    @Override
    public String getID() {
        return file.getName();
    }

    @Override
    public InputStream newInputStream() throws IOException {
        return new BufferedInputStream(new FileInputStream(file));
    }

    @Override
    public long getBytesCount() {
        return file.length();
    }
}
