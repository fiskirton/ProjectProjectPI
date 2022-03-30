package com.crm.crm.entity;

import javax.persistence.*;

@Entity
public class UserAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_answer_id")
    private Long userAnswerId;

    @Column(name = "answer_id")
    private Long answerId;

    @Column(name = "question_id")
    private Long question_id;

    @Column(name = "userNickName")
    private String userNickName;

    public UserAnswer() {
    }

    public UserAnswer(Long answerId, Long question_id, String userNickName) {
        this.answerId = answerId;
        this.question_id = question_id;
        this.userNickName = userNickName;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public Long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Long question_id) {
        this.question_id = question_id;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }
}
