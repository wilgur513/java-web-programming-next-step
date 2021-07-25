package once.ch8_1.controller.user;

import once.ch8_1.controller.Controller;
import once.ch8_1.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUpdateFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Object obj =  req.getSession().getAttribute("user");

        if(obj != null) {
            User user = (User) obj;
            req.setAttribute("user", user);
            return "/user/update.jsp";
        } else {
            return "redirect:/index";
        }
    }
}
