package book.ch5.webserver;

import org.slf4j.Logger;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.slf4j.LoggerFactory.getLogger;

public class HttpResponse {
    private static final Logger LOGGER = getLogger(HttpResponse.class);
    private DataOutputStream dos;
    private Map<String, String> headers = new HashMap<>();

    public HttpResponse(OutputStream out) {
        dos = new DataOutputStream(out);
    }

    public void forward(String url) {
        try{
            byte[] body = Files.readAllBytes(new File("./webapp/" + url).toPath());

            if(url.endsWith(".css")) {
                headers.put("Content-Type", "text/css");
            } else if(url.endsWith(".js")) {
                headers.put("Content-Type", "application/javascript");
            } else {
                headers.put("Content-Type", "text/html;charset=utf-8");
            }

            headers.put("Content-Length", String.valueOf(body.length));

            response200Header();
            responseBody(body);
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void forwardBody(String body) {
        byte[] contents = body.getBytes();
        headers.put("Content-Type", "text/html;charset=utf-8");
        headers.put("Content-Length", String.valueOf(contents.length));
        response200Header();
        responseBody(contents);
    }

    public void sendRedirect(String redirectUrl) {
        try{
            dos.writeBytes("HTTP/1.1 302 Found \r\n");
            processHeader();
            dos.writeBytes("Location: " + redirectUrl + " \r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void response200Header() {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            processHeader();
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void processHeader() throws IOException {
        Set<String> keys = headers.keySet();

        for(String key : keys) {
            dos.writeBytes(key + ": " + headers.get(key) + " \r\n");
        }
    }

    private void responseBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.writeBytes("\r\n");
            dos.flush();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }
}
