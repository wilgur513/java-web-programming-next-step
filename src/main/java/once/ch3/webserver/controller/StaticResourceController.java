package once.ch3.webserver.controller;

import once.ch3.webserver.HttpRequest;
import once.ch3.webserver.HttpResponse;
import once.ch3.webserver.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class StaticResourceController implements Controller {
    private final Logger log = LoggerFactory.getLogger(StaticResourceController.class);

    @Override
    public boolean isSupported(String url, String method) {
        return url.endsWith(".html") && method.equals("GET");
    }

    @Override
    public void handle(HttpRequest req, HttpResponse res) {
        View view = new View(req.getUrl());

        res.writeStatus(200);
        res.writeHeader("Content-Type", "text/html;charset=utf-8");
        res.writeHeader("Content-Length", String.valueOf(view.getBody().length));
        res.writeBlank();
        res.writeBody(view.getBody());
    }
}
