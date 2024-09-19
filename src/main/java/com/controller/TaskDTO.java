package com.controller;

import com.model.Task;

public record TaskDTO(String content, int priority) {

  public Task toEntity() {
    return new Task(content, priority);
  }
}
