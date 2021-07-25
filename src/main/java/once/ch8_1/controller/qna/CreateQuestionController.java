package once.ch8_1.controller.qna;

import once.ch8_1.controller.Controller;
import once.ch8_1.dao.QuestionDao;
import once.ch8_1.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.time.Instant;

public class CreateQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        Question question = new Question(null, title, writer, contents, Timestamp.from(Instant.now()), 0);
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(question);
        return "redirect:/index";
    }
}
