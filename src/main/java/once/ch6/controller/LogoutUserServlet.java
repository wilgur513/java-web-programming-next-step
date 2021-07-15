package once.ch6.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/user/logout")
public class LogoutUserServlet extends HttpServlet {
    private static final Logger LOGGER = getLogger(LogoutUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        LOGGER.debug("logout user: {}", session.getAttribute("user"));

        session.removeAttribute("user");
        resp.sendRedirect("/");
    }
}
