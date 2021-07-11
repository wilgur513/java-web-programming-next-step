package book.ch5.webserver;

import book.ch5.db.DataBase;
import book.ch5.model.User;

public class LoginController extends AbstractController{
    @Override
    protected void doPost(HttpRequest request, HttpResponse response) {
        User user = DataBase.findUserById(request.getParameter("userId"));

        if(user != null) {
            if(user.login(request.getParameter("password"))) {
                response.addHeader("Set-Cookie", "logined=true");
                response.sendRedirect("/index.html");
                return;
            }
        }

        response.sendRedirect("/user/login_failed.html");
    }
}
