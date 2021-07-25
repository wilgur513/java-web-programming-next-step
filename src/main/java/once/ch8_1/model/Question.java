package once.ch8_1.model;

import java.sql.Timestamp;

public class Question {
    private Long questionId;
    private String title;
    private String writer;
    private String contents;
    private Timestamp createdDate;
    private int countOfAnswer;

    public Question(Long questionId, String title, String writer, String contents, Timestamp createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }
}
