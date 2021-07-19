package once.ch6_2.controller;

import once.ch6_2.http.HttpRequest;
import once.ch6_2.http.HttpResponse;

public abstract class AbstractController implements Controller {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        if(request.getMethod().isPost()) {
            doPost(request, response);
        } else {
            doGet(request, response);
        }
    }

    protected void doGet(HttpRequest request, HttpResponse response) {}
    protected void doPost(HttpRequest request, HttpResponse response) {}
}
