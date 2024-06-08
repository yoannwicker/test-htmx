package com.controller;

import static org.xmlunit.assertj.XmlAssert.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class TaskListHtmlComponentTest {

  @Test
  void should_build_render() {
    // given
    TaskListHtmlComponent taskListHtmlComponent = new TaskListHtmlComponent(
        List.of(new TaskHtmlComponent(1L, "Buy milk"), new TaskHtmlComponent(2L, "Buy bread")));

    // when
    String result = taskListHtmlComponent.render().asString();

    // then
    String expected = """
        <li id='task-1'>Buy milk <button hx-delete='/tasks/1' hx-target='#task-1' hx-swap='outerHTML'>Delete</button></li>
        <li id='task-2'>Buy bread <button hx-delete='/tasks/2' hx-target='#task-2' hx-swap='outerHTML'>Delete</button></li>
        """;
    assertThat(wrapWithRoot(result)).and(wrapWithRoot(expected))
        .ignoreWhitespace().areSimilar();
  }

  private static String wrapWithRoot(String content) {
    return STR."<ul>\{content}</ul>";
  }
}
