package once.ch3.webserver;

import java.io.*;
import java.net.Socket;

import once.ch3.db.DataBase;
import once.ch3.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

            if(request.getUrl().equals("/user/create")) {
                User user = createUser(request);
                log.debug("user: {}", user);
                DataBase.addUser(user);
                response.writeView(302, null);
            } else {
                View view = new View(request.getUrl());
                response.writeView(200, view);
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    private User createUser(HttpRequest request) {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        return new User(userId, password, name, email);
    }
}
