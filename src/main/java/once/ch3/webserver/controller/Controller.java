package once.ch3.webserver.controller;

import once.ch3.webserver.HttpRequest;
import once.ch3.webserver.HttpResponse;

public interface Controller {
    boolean isSupported(String url, String method);
    void handle(HttpRequest req, HttpResponse res);
}
