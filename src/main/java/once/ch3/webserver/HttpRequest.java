package once.ch3.webserver;

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
    private List<String> requestData;
    private String url;
    private String method;

    public HttpRequest(InputStream in) {
        this.requestData = requestData(in);
        this.url = url();
        this.method = method();

        log.debug("url: {}, method: {}", url, method);
    }

    private List<String> requestData(InputStream in) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        try {
            return readHeader(reader);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> readHeader(BufferedReader reader) throws IOException {
        List<String> result = new ArrayList<>();
        String line = reader.readLine();

        while(!line.equals("") && line != null) {
            result.add(line);
            line = reader.readLine();
        }

        return result;
    }

    private String url() {
        return requestData.get(0).split(" ")[1];
    }

    private String method() {
        return requestData.get(0).split(" ")[0];
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

}
