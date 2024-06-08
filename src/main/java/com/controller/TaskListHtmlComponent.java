package com.controller;

import com.model.Task;
import java.util.List;
import java.util.stream.Collectors;

public record TaskListHtmlComponent(List<TaskHtmlComponent> tasks) {

  public String render() {
    return tasks.stream().map(TaskHtmlComponent::render).collect(Collectors.joining());
  }

  public static TaskListHtmlComponent from(List<Task> tasks) {
    return new TaskListHtmlComponent(tasks.stream().map(TaskHtmlComponent::from).toList());
  }
}
