package com.controller;

import static org.xmlunit.assertj.XmlAssert.assertThat;

import org.junit.jupiter.api.Test;

class TaskHtmlComponentTest {

  @Test
  void should_build_render() {
    // given
    TaskHtmlComponent taskHtmlComponent = new TaskHtmlComponent(1L, "Buy milk");

    // when
    String result = taskHtmlComponent.render();

    // then
    String expected = "<li id='task-1'>Buy milk <button hx-delete='/tasks/1' hx-target='#task-1' hx-swap='outerHTML'>Delete</button></li>";
    assertThat(result).and(expected).ignoreWhitespace().areIdentical();
  }
}
