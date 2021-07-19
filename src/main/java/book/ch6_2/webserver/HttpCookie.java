package book.ch6_2.webserver;

import book.ch4.util.HttpRequestUtils;

import java.util.Map;

public class HttpCookie {
    private Map<String, String> cookies;

    public HttpCookie(String cookieValue) {
        this.cookies = HttpRequestUtils.parseCookies(cookieValue);
    }

    public String getCookie(String key) {
        return cookies.get(key);
    }
}
