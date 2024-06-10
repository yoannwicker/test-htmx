package com.controller;

import com.model.Task;

public record TaskHtmlComponent(Long id, String content) {
  public String render() {
    return "<li id='task-" + id + "'>"
        + content
        + " <button hx-delete='/tasks/" + id + "' hx-target='#task-" + id + "' hx-swap='outerHTML'>Delete</button>"
        + "</li>";
  }

  public static TaskHtmlComponent from(Task task) {
    return new TaskHtmlComponent(task.getId(), task.getContent());
  }
}
