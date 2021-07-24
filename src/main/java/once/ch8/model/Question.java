package once.ch8.model;

import java.sql.Timestamp;

public class Question {
    private Long questionId;
    private String title;
    private String writer;
    private String contents;
    private Timestamp timestamp;
    private int countOfAnswer;

    public Question(Long questionId, String title, String writer, String contents, Timestamp timestamp, int countOfAnswer) {
        this.questionId = questionId;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
        this.timestamp = timestamp;
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

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }
}
