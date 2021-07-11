package book.ch5.webserver;

import book.ch5.util.HttpRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class RequestLine {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestLine.class);
    private String method;
    private String path;
    private Map<String, String> params = new HashMap<>();


    public RequestLine(String line) {
        LOGGER.debug("request line : {}", line);
        String[] tokens = line.split(" ");

        if(tokens.length != 3) {
            throw new IllegalArgumentException(line + "형식에 맞지 않습니다.");
        }

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

    public Map<String, String> getParams() {
        return params;
    }
}
