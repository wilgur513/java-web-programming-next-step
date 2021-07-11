package book.ch5.webserver;

import java.io.*;
import java.net.Socket;
import java.util.Collection;
import java.util.Map;

import book.ch5.db.DataBase;
import book.ch5.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import book.ch5.util.HttpRequestUtils;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            HttpRequest request = new HttpRequest(in);
            HttpResponse response = new HttpResponse(out);
            String url = getDefaultPath(request.getPath());

            if(url.equals("/user/create")) {
                User user = new User(
                        request.getParameter("userId"),
                        request.getParameter("password"),
                        request.getParameter("name"),
                        request.getParameter("email"));
                log.debug("User : {}", user);
                DataBase.addUser(user);
                response.sendRedirect("/index.html");
            } else if(url.equals("/user/list")) {
                if(!isLogin(request.getHeader("Cookie"))) {
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
            } else if(url.equals("/user/login")) {
                User user = DataBase.findUserById(request.getParameter("userId"));

                if(user != null) {
                    if(user.login(request.getParameter("password"))) {
                        response.addHeader("Set-Cookie", "logined=true");
                        response.sendRedirect("/index.html");
                        return;
                    }
                }

                response.sendRedirect("/user/login_failed.html");
            } else {
                response.forward(url);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private String getDefaultPath(String path) {
        if(path.equals("/")) {
            return "/index.html";
        }

        return path;
    }

    private boolean isLogin(String cookieValue) {
        Map<String, String> cookies = HttpRequestUtils.parseCookies(cookieValue);

        if(cookies.get("logined") == null) {
            return false;
        }

        return Boolean.parseBoolean(cookies.get("logined"));
    }
}