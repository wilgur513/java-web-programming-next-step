package once.ch6_2.controller;

import once.ch6_2.http.HttpRequest;
import once.ch6_2.http.HttpResponse;

public interface Controller {
    void service(HttpRequest request, HttpResponse response);
}
