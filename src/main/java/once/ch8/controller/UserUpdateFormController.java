package once.ch8.controller;

import once.ch8.model.User;

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
            return "redirect:/index.jsp";
        }
    }
}
