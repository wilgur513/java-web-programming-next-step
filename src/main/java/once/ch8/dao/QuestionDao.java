package once.ch8.dao;

import once.ch8.dao.core.JdbcTemplate;
import once.ch8.model.Question;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {
    private JdbcTemplate template = new JdbcTemplate();

    public void insert(Question question) {
        template.update("INSERT INTO Questions VALUES (?, ?, ?, ?, ?, ?)",
            question.getQuestionId(), question.getTitle(), question.getWriter(),
            question.getContents(), question.getTimestamp(), question.getCountOfAnswer());
    }

    public List<Question> findAll() {
        return template.query("SELECT * FROM Questions", (rs) -> toQuestion(rs));
    }

    public Question findById(long id) {
        return template.queryForObject("SELECT * FROM Questions AS q WHERE q.id=?", (rs) -> toQuestion(rs), id);
    }

    private Question toQuestion(ResultSet rs) throws SQLException {
        return new Question(rs.getLong("questionId"), rs.getString("title"), rs.getString("writer"),
                rs.getString("contents"), rs.getTimestamp("timestamp"), rs.getInt("countOfAnswer"));
    }
}
