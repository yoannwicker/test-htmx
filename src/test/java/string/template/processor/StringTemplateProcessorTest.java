package string.template.processor;

import static org.assertj.core.api.Assertions.assertThat;

import static java.lang.StringTemplate.RAW;

import java.util.List;
import org.json.JSONObject;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class StringTemplateProcessorTest {

  @Nested
  class StringTemplateTest {

    @Test
    void syntax() {
      var name = "John";
      var age = 30;

      String result = STR."My name is \{name}. My age is \{age}.";

      assertThat(result)
          .isEqualTo("My name is John. My age is 30.");
    }

    @Test
    void stringTemplate() {
      var name = "John";
      var age = 30;

      StringTemplate result = RAW."My name is \{name}. My age is \{age}.";

      assertThat(result.values())
          .containsExactly("John", 30);
      assertThat(result.fragments())
          .containsExactly("My name is ", ". My age is ", ".");
      assertThat(STR.process(result))
          .isEqualTo(result.interpolate())
          .isEqualTo("My name is John. My age is 30.");
    }

    @Test
    void combine() {
      var name = "John";
      var age = 30;
      StringTemplate nameTmpl = RAW."My name is \{name}. ";
      StringTemplate ageTmpl = RAW."My age is \{age}.";

      String result = STR.process(StringTemplate.combine(List.of(nameTmpl, ageTmpl)));

      assertThat(result)
          .isEqualTo("My name is John. My age is 30.");
    }
  }

  @Nested
  class TemplateProcessorTest {

    class JsonTemplateProcessor implements StringTemplate.Processor<JSONObject, Exception> {

      @Override
      public JSONObject process(StringTemplate tmpl) throws Exception {

        String jsonStr = tmpl.interpolate();
        return new JSONObject(jsonStr);
      }
    }

    @Test
    void jsonTemplateProcessor() throws Exception {
      var name = "John";
      var age = 30;
      var JST = new JsonTemplateProcessor();

      JSONObject result = JST."""
      {
          name : "\{name}",>
          age : \{age}
      }
      """;

      assertThat(result.get("name")).isEqualTo("John");
      assertThat(result.toString()).isEqualTo("{\"name\":\"John\",\"age\":30}");
    }

    @Test
    void jsonTemplateProcessorLambda() throws Exception {
      var name = "John";
      var age = 30;
      StringTemplate.Processor<JSONObject, Exception> JST = t -> {
        String jsonStr = t.interpolate();
        return new JSONObject(jsonStr);
      };

      JSONObject result = JST."""
      {
          name : "\{name}",
          age : \{age}
      }
      """;

      assertThat(result.toString()).isEqualTo("{\"name\":\"John\",\"age\":30}");
    }

    @Test
    void limit() throws Exception {
      var name = "John";
      var age = 30;
      var JST = new JsonTemplateProcessor();

      JSONObject address = JST."""
      {
          postalCode : "\{"92130"}",
          city : "\{"Issy"}"
      }
      """;
      JSONObject user = JST."""
      {
          name : "\{name}",
          age : \{age},
          address : \{address.toString()}
      }
      """;

    }
  }
}
