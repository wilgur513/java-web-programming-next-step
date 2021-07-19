package once.ch6.controller;

import once.ch6.db.DataBase;
import once.ch6.model.User;
import org.slf4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

//@WebServlet("/user/login")
public class LoginUserController implements Controller {
    private static final Logger LOGGER = getLogger(LoginUserController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = DataBase.findUserById(userId);

        LOGGER.debug("login user : {}", user);

        if(user != null && user.getPassword().equals(password)) {
            req.getSession().setAttribute("user", user);
            return "redirect:/index.jsp";
        } else {
            req.setAttribute("userId", userId);
            return "/user/login_failed.jsp";
        }
    }
}
