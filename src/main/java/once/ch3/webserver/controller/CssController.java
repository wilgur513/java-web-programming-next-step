package once.ch3.webserver.controller;

import once.ch3.webserver.HttpRequest;
import once.ch3.webserver.HttpResponse;
import once.ch3.webserver.View;

public class CssController implements Controller{
    @Override
    public boolean isSupported(String url, String method) {
        return url.endsWith(".css") && method.equals("GET");
    }

    @Override
    public void handle(HttpRequest req, HttpResponse res) {
        View view = new View(req.getUrl());

        res.writeStatus(200);
        res.writeHeader("Content-Type", "text/css;charset=utf-8");
        res.writeHeader("Content-Length", String.valueOf(view.getBody().length));
        res.writeBlank();
        res.writeBody(view.getBody());
    }
}
