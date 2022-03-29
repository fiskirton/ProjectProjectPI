package com.crm.crm.entity;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;//TODO: пробросить связи
    private Long questionNumber;
    private Long testId;
    private String text;

    public Question() {
    }

    public Question(Long questionId, Long questionNumber, Long testId, String text) {
        this.questionId = questionId;
        this.questionNumber = questionNumber;
        this.testId = testId;
        this.text = text;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(Long questionNumber) {
        this.questionNumber = questionNumber;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
