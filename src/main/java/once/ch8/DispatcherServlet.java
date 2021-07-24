package once.ch8;

import once.ch8.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/", name = "dispatcher", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getServletPath();

        Controller controller = RequestMapping.find(url);
        String viewName = controller.execute(req, resp);

        LOGGER.debug("url: {}, controller: {}, view: {}", url, controller.getClass().getSimpleName(), viewName);

        if(viewName.startsWith("redirect:")) {
            resp.sendRedirect(viewName.substring("redirect:".length()));
            return;
        }

        RequestDispatcher rd = req.getRequestDispatcher(viewName);
        rd.forward(req, resp);
    }
}
