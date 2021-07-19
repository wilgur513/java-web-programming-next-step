package once.ch6_2.controller;

import once.ch6_2.db.DataBase;
import once.ch6_2.http.HttpSession;
import once.ch6_2.model.User;
import once.ch6_2.util.HttpRequestUtils;
import once.ch6_2.http.HttpRequest;
import once.ch6_2.http.HttpResponse;

import java.util.Collection;
import java.util.Map;

public class ListUserController extends AbstractController {
    @Override
    protected void doGet(HttpRequest request, HttpResponse response) {
        if(!isLogin(request.getSession())) {
            response.sendRedirect("/user/login.html");
            return;
        }

        Collection<User> users = DataBase.findAll();
        StringBuilder sb = new StringBuilder();
        sb.append("<table border='1'>");
        for(User user : users) {
            sb.append("<tr>");
            sb.append("<td>" + user.getUserId() + "</td>");
            sb.append("<td>" + user.getName() + "</td>");
            sb.append("<td>" + user.getEmail() + "</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");

        response.forwardBody(sb.toString());
    }

    private boolean isLogin(HttpSession session) {
        Object user = session.getAttribute("user");
        return user != null ? true : false;
    }
}
