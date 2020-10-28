package space.banka.jiffy.api;

import java.io.IOException;
import java.io.OutputStream;

public interface WriteableAnswer {

    OutputStream newOutputStream() throws IOException;
}
