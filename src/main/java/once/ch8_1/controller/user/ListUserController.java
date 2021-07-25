package once.ch8_1.controller.user;

import once.ch8_1.controller.Controller;
import once.ch8_1.dao.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController implements Controller {
    private static final Logger LOGGER = LoggerFactory.getLogger(ListUserController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("user");

        if(obj != null) {
            UserDao userDao = new UserDao();
            req.setAttribute("users", userDao.findAll());
            return "/user/list.jsp";
        } else {
            return "redirect:/user/login";
        }
    }
}
