package once.ch6.controller;

import once.ch6.db.DataBase;
import once.ch6.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
    private static final Logger LOGGER = getLogger(LoginUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = DataBase.findUserById(userId);

        LOGGER.debug("login user : {}", user);

        if(user != null && user.getPassword().equals(password)) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/");
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.jsp");
            req.setAttribute("userId", userId);
            rd.forward(req, resp);
        }
    }
}
