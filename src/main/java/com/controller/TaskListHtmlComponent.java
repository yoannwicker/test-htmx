package com.controller;

import com.controller.htmlcomponent.Component;
import com.controller.htmlcomponent.Renderer;
import com.model.Task;
import java.util.Comparator;
import java.util.List;

public record TaskListHtmlComponent(List<TaskHtmlComponent> tasks) implements Component {

  @Override
  public Renderer render() {
    return Renderer.from(tasks.stream());
  }

  public static TaskListHtmlComponent from(List<Task> tasks) {
    var taskHtmlComponents = tasks.stream()
        .map(TaskHtmlComponent::from)
        .sorted(Comparator.comparing(TaskHtmlComponent::priority)
            .thenComparing(TaskHtmlComponent::content))
        .toList();
    return new TaskListHtmlComponent(taskHtmlComponents);
  }
}
