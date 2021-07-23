package once.ch7.controller;

import once.ch7.dao.UserDao;
import once.ch7.model.User;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.SQLException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserUpdateController implements Controller {
    private static final Logger LOGGER = getLogger(UserUpdateController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(userId, password, name, email);

        LOGGER.debug("updated user : {}", user);

        try {
            UserDao userDao = new UserDao();
            userDao.update(user);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }

        return "redirect:/user/list";
    }
}
