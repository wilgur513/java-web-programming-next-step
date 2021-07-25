package once.ch8_1.controller.user;

import once.ch8_1.controller.Controller;
import once.ch8_1.dao.UserDao;
import once.ch8_1.model.User;
import org.slf4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.slf4j.LoggerFactory.getLogger;

public class LoginUserController implements Controller {
    private static final Logger LOGGER = getLogger(LoginUserController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user;

        UserDao userDao = new UserDao();
        user = userDao.findByUserId(userId);
        LOGGER.debug("login user : {}", user);

        if(user != null && user.getPassword().equals(password)) {
            req.getSession().setAttribute("user", user);
            return "redirect:/index";
        } else {
            req.setAttribute("userId", userId);
            return "/user/login_failed.jsp";
        }
    }
}
