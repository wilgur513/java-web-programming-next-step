package once.ch8.dao;

import once.ch8.dao.core.JdbcTemplate;
import once.ch8.model.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnswerDao {
    private JdbcTemplate template = new JdbcTemplate();

    public void insert(Answer answer) {
        template.update("INSERT INTO ANSWERS VALUES (?, ?, ?, ?, ?)",
                answer.getAnswerId(), answer.getWriter(), answer.getContents(), answer.getCreatedDate(), answer.getQuestionId());
    }

    public List<Answer> findAll() {
        return template.query("SELECT * FROM ANSWERS", this::toAnswer);
    }

    private Answer toAnswer(ResultSet rs) throws SQLException {
        return new Answer(rs.getLong("answerId"), rs.getString("writer"), rs.getString("contents"),
            rs.getTimestamp("createdDate"), rs.getLong("questionId"));
    }
}
