package space.banka.jiffy.api;

import java.io.IOException;
import java.io.InputStream;

public interface Answer {

    String getID();

    InputStream newInputStream() throws IOException;

    long getBytesCount();
}
