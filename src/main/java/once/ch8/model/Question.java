package once.ch8.model;

import java.time.LocalDate;

public class Question {
    private Long questionId;
    private String title;
    private String writer;
    private String content;
    private LocalDate timestamp;
    private int countOfAnswer;

    public Question(Long questionId, String title, String writer, String content, LocalDate timestamp, int countOfAnswer) {
        this.questionId = questionId;
        this.title = title;
        this.writer = writer;
        this.content = content;
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

    public String getContent() {
        return content;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }
}
