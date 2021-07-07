package once.ch3.webserver.controller;

import once.ch3.db.DataBase;
import once.ch3.model.User;
import once.ch3.webserver.HttpRequest;
import once.ch3.webserver.HttpResponse;
import once.ch3.webserver.controller.Controller;

public class JoinController implements Controller {
    @Override
    public boolean isSupported(String url, String method) {
        return url.equals("/user/create") && method.equals("POST");
    }

    @Override
    public void handle(HttpRequest req, HttpResponse res) {
        User user = createUser(req);
        DataBase.addUser(user);

        res.writeHeader(302, 0);
    }

    private User createUser(HttpRequest request) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        return new User(userId, password, name, email);
    }
}
