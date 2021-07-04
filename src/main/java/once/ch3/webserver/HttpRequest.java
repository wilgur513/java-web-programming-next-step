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
import java.util.Map;

public class HttpRequest {
    private static final Logger log = LoggerFactory.getLogger(HttpRequest.class);
    private BufferedReader reader;
    private List<String> requestData;
    private String url;
    private String method;
    private Map<String, String> params;

    public HttpRequest(InputStream in) {
        this.reader = new BufferedReader(new InputStreamReader(in));
        this.requestData = requestData(reader);
        this.url = url();
        this.method = method();
        this.params = params();
        log.debug("url: {}, method: {}, queryString: {}", url, method, queryString());
    }

    private List<String> requestData(BufferedReader reader) {
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
        if(requestData.get(0).split(" ")[1].contains("?")) {
            int index = requestData.get(0).split(" ")[1].indexOf("?");
            return requestData.get(0).split(" ")[1].substring(0, index);
        }

        return requestData.get(0).split(" ")[1];
    }

    private String queryString() {
        if(requestData.get(0).split(" ")[1].contains("?")) {
            int index = requestData.get(0).split(" ")[1].indexOf("?");
            return requestData.get(0).split(" ")[1].substring(index + 1);
        }

        return "";
    }

    private String method() {
        return requestData.get(0).split(" ")[0];
    }

    private Map<String, String> params() {
        return HttpRequestUtils.parseQueryString(queryString());
    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }

    public String getParameter(String key) {
        return params.get(key);
    }
}
