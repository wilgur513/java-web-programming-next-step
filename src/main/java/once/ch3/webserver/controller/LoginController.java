package once.ch3.webserver.controller;

import once.ch3.db.DataBase;
import once.ch3.model.User;
import once.ch3.webserver.HttpRequest;
import once.ch3.webserver.HttpResponse;
import once.ch3.webserver.View;

public class LoginController implements Controller{
    @Override
    public boolean isSupported(String url, String method) {
        return url.equals("/user/login") && method.equals("POST");
    }

    @Override
    public void handle(HttpRequest req, HttpResponse res) {
        User user = DataBase.findUserById(req.getParameter("userId"));

        if(user == null || !user.getPassword().equals(req.getParameter("password"))) {
            res.writeStatus(302);
            res.writeHeader("Location", "/user/login_failed.html");
            res.writeHeader("Set-Cookie", "logined=false");
            res.writeBlank();
        } else {
            res.writeStatus(302);
            res.writeHeader("Location", "/index.html");
            res.writeHeader("Set-Cookie", "logined=true");
            res.writeBlank();
        }
    }
}
