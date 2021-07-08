package once.ch3.webserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HttpResponse {
    private final static Logger log = LoggerFactory.getLogger(HttpResponse.class);
    private DataOutputStream dos;

    public HttpResponse(OutputStream out) {
        this.dos = new DataOutputStream(out);
    }

    public void writeStatus(int statusCode) {
        if(statusCode == 200) {
            writeHeader("HTTP/1.1 200 OK");
        } else if(statusCode == 302) {
            writeHeader("HTTP/1.1 302 Found");
        }
    }

    public void writeHeader(String key, String value) {
        writeHeader(key + ": " + value);
    }

    private void writeHeader(String text) {
        try {
            dos.writeBytes(text + "\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    public void writeBlank() {
        writeHeader("");
    }

    public void writeBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
