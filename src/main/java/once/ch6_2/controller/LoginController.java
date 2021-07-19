package once.ch6_2.controller;

import once.ch6_2.db.DataBase;
import once.ch6_2.http.HttpSession;
import once.ch6_2.model.User;
import once.ch6_2.http.HttpRequest;
import once.ch6_2.http.HttpResponse;

public class LoginController extends AbstractController {
    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        User user = DataBase.findUserById(request.getParameter("userId"));

        if(user != null) {
            if(user.login(request.getParameter("password"))) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                response.addHeader("Set-Cookie", "JSESSIONID=" + session.getId());
                response.sendRedirect("/index.html");
                return;
            }
        }

        response.sendRedirect("/user/login_failed.html");
    }
}
