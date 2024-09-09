package com.controller;

import com.controller.htmlcomponent.Component;
import com.controller.htmlcomponent.Renderer;
import com.model.Task;

public record TaskHtmlComponent(Long id, String content, int priority) implements Component {

  @Override
  public Renderer render() {
    return $."""
        <li id='task-\{id}'>
          <span class="number-circle">\{priority}</span>
          \{content}
          <button hx-delete='/tasks/\{id}' hx-target='#task-\{id}' hx-swap='outerHTML'>Delete</button>
        </li>
        """;
  }

  public static TaskHtmlComponent from(Task task) {
    return new TaskHtmlComponent(task.getId(), task.getContent(), task.getPriority());
  }
}
