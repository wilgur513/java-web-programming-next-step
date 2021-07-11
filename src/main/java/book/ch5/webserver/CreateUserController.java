package book.ch5.webserver;

import book.ch5.db.DataBase;
import book.ch5.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateUserController extends AbstractController{
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
