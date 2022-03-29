package com.crm.crm.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Rating {
    @Id
    private String userNickName;
    private Long score;

    public Rating() {
    }

    public Rating(String userNickName, Long score) {
        this.userNickName = userNickName;
        this.score = score;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public Long getScore() {
        return score;
    }

}
