package book.ch5.webserver;

import book.ch5.util.IOUtils;
import book.ch5.util.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequest.class);
    private String method;
    private String path;
    private Map<String, String> params = new HashMap<>();
    private Map<String, String> headers = new HashMap<>();

    public HttpRequest(InputStream in) {
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line = reader.readLine();

            if(line == null) {
                return;
            }

            processRequestLine(line);

            line = reader.readLine();
            while(!line.equals("")) {
                LOGGER.debug("header: {}", line);
                String[] tokens = line.split(":");
                headers.put(tokens[0].trim(), tokens[1].trim());
                line = reader.readLine();
            }

            if(method.equals("POST")) {
                String body = IOUtils.readData(reader, Integer.parseInt(headers.get("Content-Length")));
                params = HttpRequestUtils.parseQueryString(body);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void processRequestLine(String line) {
        String[] tokens = line.split(" ");
        method = tokens[0];

        if(method.equals("POST")) {
            path = tokens[1];
            return;
        }

        int index = tokens[1].indexOf("?");

        if(index == -1) {
            path = tokens[1];
        } else {
            path = tokens[1].substring(0, index);
            params = HttpRequestUtils.parseQueryString(tokens[1].substring(index + 1));
        }
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    public String getParameter(String key) {
        return params.get(key);
    }
}
