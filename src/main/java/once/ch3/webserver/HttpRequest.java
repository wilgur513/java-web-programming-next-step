package once.ch3.webserver;

import once.ch3.util.HttpRequestUtils;
import once.ch3.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class HttpRequest {
    private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);
    private Header header;
    private Body body;

    public HttpRequest(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        this.header = new Header(readHeaders(reader));
        this.body = new Body(readBodies(reader));
        log.debug("header: {}, body: {}", header, body);
    }

    private List<String> readHeaders(BufferedReader reader) {
        try {
            List<String> result = new ArrayList<>();
            String line = reader.readLine();

            while(!line.equals("") && line != null) {
                result.add(line);
                line = reader.readLine();
            }

            return result;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String readBodies(BufferedReader reader) {
        try {
            int length = 0;

            if(header.getAttribute("Content-Length") != null) {
                length = Integer.parseInt(header.getAttribute("Content-Length"));
            }

            return IOUtils.readData(reader, length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUrl() {
        return header.getUrl();
    }

    public String getMethod() {
        return header.getMethod();
    }

    public String getAttribute(String key) {
        return header.getAttribute(key);
    }

    public String getParameter(String key) {
        if(getMethod().equals("GET")) {
            return header.getParameter(key);
        }

        return body.getParameter(key);
    }
}
