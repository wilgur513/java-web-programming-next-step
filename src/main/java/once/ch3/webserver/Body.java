package once.ch3.webserver;

import once.ch3.util.HttpRequestUtils;

import java.util.Map;

public class Body {
    private final Map<String, String> params;

    public Body(String body) {
        params = HttpRequestUtils.parseQueryString(body);
    }

    public String getParameter(String key) {
        return params.get(key);
    }

    public String toString() {
        return String.format("params : %s", params);
    }
}
