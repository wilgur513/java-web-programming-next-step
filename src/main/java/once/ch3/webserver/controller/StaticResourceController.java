package once.ch3.webserver.controller;

import once.ch3.webserver.HttpRequest;
import once.ch3.webserver.HttpResponse;
import once.ch3.webserver.View;

import java.util.Arrays;
import java.util.List;

public class StaticResourceController implements Controller {
    @Override
    public boolean isSupported(String url, String method) {
        List<String> urls = Arrays.asList("/index.html", "/user/form.html");

        return urls.contains(url) && method.equals("GET");
    }

    @Override
    public void handle(HttpRequest req, HttpResponse res) {
        View view = new View(req.getUrl());
        res.writeHeader(200, view.getBody().length);
        res.writeBody(view.getBody());
    }
}
