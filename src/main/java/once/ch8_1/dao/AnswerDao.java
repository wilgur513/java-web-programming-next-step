package once.ch8_1.dao;

import once.ch8_1.dao.core.JdbcTemplate;
import once.ch8_1.model.Answer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AnswerDao {
    private JdbcTemplate template = new JdbcTemplate();

    public void insert(Answer answer) {
        template.update("INSERT INTO ANSWERS (writer, contents, createdDate, questionId) VALUES (?, ?, ?, ?)",
                answer.getWriter(), answer.getContents(), answer.getCreatedDate(), answer.getQuestionId());
    }

    public List<Answer> findAll() {
        return template.query("SELECT * FROM ANSWERS", this::toAnswer);
    }

    public List<Answer> findByQuestionId(Long questionId) {
        return template.query("SELECT * FROM ANSWERS AS A WHERE A.questionId=?", this::toAnswer, questionId);
    }

    private Answer toAnswer(ResultSet rs) throws SQLException {
        return new Answer(rs.getLong("answerId"), rs.getString("writer"), rs.getString("contents"),
            rs.getTimestamp("createdDate"), rs.getLong("questionId"));
    }
}
