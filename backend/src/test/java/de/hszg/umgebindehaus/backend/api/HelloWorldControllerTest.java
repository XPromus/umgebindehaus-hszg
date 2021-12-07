package de.hszg.umgebindehaus.backend.api;

import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.List;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class HelloWorldControllerTest extends AbstractTestNGSpringContextTests {

    @Test
    public void helloWorldTest() {

        RestAssured.port=8080;

        final String result = RestAssured.get("/helloWorld").then().
                statusCode(200)
                .extract().body().asString();

        Assertions.assertThat(result).isEqualTo("Hello World!");

    }

}
