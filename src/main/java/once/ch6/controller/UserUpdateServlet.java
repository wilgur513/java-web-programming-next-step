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

@WebServlet("/user/update")
public class UserUpdateServlet extends HttpServlet {
    private static final Logger LOGGER = getLogger(UserUpdateServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object obj =  req.getSession().getAttribute("user");

        if(obj != null) {
            User user = (User) obj;
            req.setAttribute("user", user);
            RequestDispatcher rd = req.getRequestDispatcher("/user/update.jsp");
            rd.forward(req, resp);
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        User user = new User(userId, password, name, email);

        LOGGER.debug("updated user : {}", user);

        DataBase.addUser(user);
        resp.sendRedirect("/user/list");
    }
}
