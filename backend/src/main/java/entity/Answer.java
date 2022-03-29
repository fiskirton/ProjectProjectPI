package entity;
import javax.persistence.*;

@Entity
@Table(name = "answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "answer_id")
    private Long answerId;//TODO: пробросить связи
    @Column(name = "question_id")
    private Long questionId;//TODO: пробросить связи
    @Column(name = "text")
    private String text;
    @Column(name = "isRight")
    private Boolean isRight;

    public Answer() {
    }

    public Answer(Long answerId, Long questionId, String text, Boolean isRight) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.text = text;
        this.isRight = isRight;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }
}
