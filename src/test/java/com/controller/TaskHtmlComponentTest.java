package com.controller;

import static org.xmlunit.assertj.XmlAssert.assertThat;

import org.junit.jupiter.api.Test;

class TaskHtmlComponentTest {

  @Test
  void should_build_render() {
    // given
    TaskHtmlComponent taskHtmlComponent = new TaskHtmlComponent(1L, "Buy milk", 1);

    // when
    String result = taskHtmlComponent.render().asString();

    // then
    String expected = "<li id='task-1'><span class='number-circle'>1</span> Buy milk <button hx-delete='/tasks/1' hx-target='#task-1' hx-swap='outerHTML'>Delete</button></li>";
    assertThat(result).and(expected).ignoreWhitespace().areIdentical();
  }
}
