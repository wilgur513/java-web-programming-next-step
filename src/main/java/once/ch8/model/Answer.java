package once.ch8.model;

import java.time.LocalDate;

public class Answer {
    private Long answerId;
    private String writer;
    private String contents;
    private LocalDate createdDate;
    private Long questionId;

    public Answer(Long answerId, String writer, String contents, LocalDate createdDate, Long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public Long getQuestionId() {
        return questionId;
    }
}
