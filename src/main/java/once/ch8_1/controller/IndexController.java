package once.ch8_1.controller;

import once.ch8_1.dao.QuestionDao;
import once.ch8_1.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class IndexController implements Controller{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        QuestionDao dao = new QuestionDao();
        List<Question> questions = dao.findAll();
        req.setAttribute("questions", questions);
        return "/index.jsp";
    }
}
