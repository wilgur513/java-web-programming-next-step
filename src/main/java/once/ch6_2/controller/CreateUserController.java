package once.ch6_2.controller;

import once.ch6_2.db.DataBase;
import once.ch6_2.model.User;
import once.ch6_2.http.HttpRequest;
import once.ch6_2.http.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateUserController extends AbstractController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CreateUserController.class);

    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        User user = new User(
                request.getParameter("userId"),
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"));
        LOGGER.debug("User : {}", user);
        DataBase.addUser(user);
        response.sendRedirect("/index.html");
    }
}
