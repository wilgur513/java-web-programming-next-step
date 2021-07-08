package once.ch3.webserver.controller;

import once.ch3.db.DataBase;
import once.ch3.model.User;
import once.ch3.util.HttpRequestUtils;
import once.ch3.webserver.HttpRequest;
import once.ch3.webserver.HttpResponse;
import once.ch3.webserver.View;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static once.ch3.db.DataBase.findAll;

public class UserListController implements Controller{
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public boolean isSupported(String url, String method) {
        return url.equals("/user/list") && method.equals("GET");
    }

    @Override
    public void handle(HttpRequest req, HttpResponse res) {
        Map<String, String> cookies = HttpRequestUtils.parseCookies(req.getAttribute("Cookie"));

        boolean logined = Boolean.parseBoolean(cookies.get("logined"));

        log.debug("cookies: {}, logined: {}", cookies, logined);

        res.writeStatus(200);
        res.writeHeader("Content-Type", "text/html;charset=utf-8");

        if(logined) {
            Collection<User> users = DataBase.findAll();
            StringBuilder builder = new StringBuilder("<html><body>");
            for(User user : users) {
                builder.append("-------------------\r\n");
                builder.append("id: " + user.getUserId() + "\r\n");
                builder.append("name: " + user.getName() + "\r\n");
            }
            builder.append("</body></html>");

            res.writeHeader("Content-Length", String.valueOf(builder.toString().length()));
            res.writeBlank();
            res.writeBody(builder.toString().getBytes());
        } else {
            View view = new View("/user/login.html");
            res.writeHeader("Content-Length", String.valueOf(view.getBody().length));
            res.writeBlank();
            res.writeBody(view.getBody());
        }
    }
}
