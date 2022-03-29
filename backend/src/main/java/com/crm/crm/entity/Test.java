package com.crm.crm.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Test {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="test_id")
    @NotNull
    private Long testId; //TODO: пробросить связи

    @Column(name="title")
    @NotNull
    private String title;

    @Column(name="startAt")
    @NotNull
    private LocalDateTime startAt;

    @Column(name="endAt")
    @NotNull
    private LocalDateTime endAt;

    public Test() {
    }

    public Test(Long testId, String title, LocalDateTime startAt, LocalDateTime endAt) {
        this.testId = testId;
        this.title = title;
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalDateTime startAt) {
        this.startAt = startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalDateTime endAt) {
        this.endAt = endAt;
    }
}
