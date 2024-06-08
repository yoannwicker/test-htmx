package com.controller;

import com.controller.htmlcomponent.Component;
import com.controller.htmlcomponent.Renderer;
import com.model.Task;
import java.util.List;
import java.util.stream.Collectors;

public record TaskListHtmlComponent(List<TaskHtmlComponent> tasks) implements Component {

  @Override
  public Renderer render() {
    return Renderer.from(tasks.stream());
  }

  public static TaskListHtmlComponent from(List<Task> tasks) {
    return new TaskListHtmlComponent(tasks.stream().map(TaskHtmlComponent::from).toList());
  }
}
