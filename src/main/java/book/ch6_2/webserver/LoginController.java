package book.ch6_2.webserver;

import book.ch6_2.db.DataBase;
import book.ch6_2.model.User;

public class LoginController extends AbstractController {
    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        User user = DataBase.findUserById(request.getParameter("userId"));

        if(user != null) {
            if(user.login(request.getParameter("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.sendRedirect("/index.html");
                return;
            }
        }

        response.sendRedirect("/user/login_failed.html");
    }
}
