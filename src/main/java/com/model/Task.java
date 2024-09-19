package com.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private int priority;

    public Task(String content, int priority) {
        this.content = content;
        this.priority = priority;
    }

    public Task() {

    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getPriority() {
      return priority;
    }
}
