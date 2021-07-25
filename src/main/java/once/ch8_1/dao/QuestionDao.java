package once.ch8_1.dao;

import once.ch8_1.dao.core.JdbcTemplate;
import once.ch8_1.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    private JdbcTemplate template = new JdbcTemplate();

    public void insert(Question question) {
        template.update("INSERT INTO Questions (title, writer, contents, createdDate, countOfAnswer) VALUES (?, ?, ?, ?, ?)",
            question.getTitle(), question.getWriter(),
            question.getContents(), question.getCreatedDate(), question.getCountOfAnswer());
    }

    public List<Question> findAll() {
        return template.query("SELECT * FROM QUESTIONS", (rs) -> toQuestion(rs));
    }

    public Question findById(long id) {
        return template.queryForObject("SELECT * FROM QUESTIONS AS q WHERE q.questionId=?", (rs) -> toQuestion(rs), id);
    }

    private Question toQuestion(ResultSet rs) throws SQLException {
        return new Question(rs.getLong("questionId"), rs.getString("title"), rs.getString("writer"),
                rs.getString("contents"), rs.getTimestamp("createdDate"), rs.getInt("countOfAnswer"));
    }
}
