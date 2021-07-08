package once.ch3.webserver;

import once.ch3.util.HttpRequestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Header {
    private final String[] firstLine;
    private final Map<String, String> headers;
    private final Map<String, String> params;

    public Header(List<String> lines) {
        this.firstLine = lines.get(0).split(" ");
        this.headers = initHeaders(lines);
        this.params = initParams();
    }

    private Map<String, String> initHeaders(List<String> lines) {
        Map<String, String> map = new HashMap<>();
        for(int i = 1; i < lines.size(); i++) {
            HttpRequestUtils.Pair pair = HttpRequestUtils.parseHeader(lines.get(i));
            map.put(pair.getKey(), pair.getValue());
        }
        return map;
    }

    private Map<String, String> initParams() {
        return HttpRequestUtils.parseQueryString(queryString());
    }

    private String queryString() {
        return fullUrl().substring(queryIndex() + 1);
    }

    private String fullUrl() {
        return firstLine[1];
    }

    private int queryIndex() {
        return fullUrl().indexOf("?");
    }

    public String getAttribute(String key) {
        if(headers.containsKey(key)) {
            return headers.get(key);
        }

        return null;
    }

    public String getMethod() {
        return firstLine[0];
    }

    public String getUrl() {
        if(queryIndex() != -1) {
            return fullUrl().substring(0, queryIndex());
        }

        return fullUrl();
    }

    public String getParameter(String key) {
        return params.get(key);
    }

    public String toString() {
        return String.format("url: %s, method: %s, headers : %s, params: %s", getUrl(), getMethod(), headers, params);
    }
}
