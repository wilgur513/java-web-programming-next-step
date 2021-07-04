package once.ch3.webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class View {
    private String url;
    private final static Logger log = LoggerFactory.getLogger(View.class);

    public View(String url) {
        this.url = url;
    }

    public byte[] getBody() {
        File file = new File("webapp/" + url);
        log.debug("file: {}", file.getAbsolutePath());

        try {
            return Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
