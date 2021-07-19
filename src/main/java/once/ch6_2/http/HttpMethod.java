package once.ch6_2.http;

public enum HttpMethod {
    GET, POST;

    public boolean isPost() {
        return this == POST;
    }
}
