package once.ch6.controller;

import org.slf4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

//@WebServlet("/user/logout")
public class LogoutUserController implements Controller {
    private static final Logger LOGGER = getLogger(LogoutUserController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();

        LOGGER.debug("logout user: {}", session.getAttribute("user"));

        session.removeAttribute("user");
        return "redirect:/index.jsp";
    }
}
