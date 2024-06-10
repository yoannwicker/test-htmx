package com.controller;

import com.model.Task;
import java.util.List;

public record TaskListHtmlComponent(List<TaskHtmlComponent> tasks) {
  public String render() {
    StringBuilder sb = new StringBuilder();
    for (TaskHtmlComponent task : tasks) {
      sb.append(task.render());
    }
    return sb.toString();
  }

  public static TaskListHtmlComponent from(List<Task> tasks) {
    return new TaskListHtmlComponent(tasks.stream().map(TaskHtmlComponent::from).toList());
  }
}
