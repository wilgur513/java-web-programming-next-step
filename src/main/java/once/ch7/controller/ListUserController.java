package once.ch7.controller;

import once.ch7.db.DataBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Object obj = session.getAttribute("user");

        if(obj != null) {
            req.setAttribute("users", DataBase.findAll());
            return "/user/list.jsp";
        } else {
            return "redirect:/user/login";
        }
    }
}
