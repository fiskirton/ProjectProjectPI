package com.crm.crm.entity;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long answerId;//TODO: пробросить связи

    private Long questionId;//TODO: пробросить связи

    private String text;

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
