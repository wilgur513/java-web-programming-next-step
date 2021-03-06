package once.ch3.webserver;

import java.io.*;
import java.net.Socket;

import once.ch3.webserver.controller.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
    private final Controller[] controllers = {
            new StaticResourceController(),
            new JoinController(),
            new LoginController(),
            new CssController(),
            new UserListController()
    };

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

            for(Controller controller : controllers) {
                if(controller.isSupported(request.getUrl(), request.getMethod())) {
                    controller.handle(request, response);
                    break;
                }
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }


}
