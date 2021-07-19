package once.ch6.controller;

import once.ch6.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUpdateFormController implements Controller{
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
