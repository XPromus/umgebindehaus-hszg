package de.hszg.umgebindehaus.backend.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest (
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)

public class ScenarioControllerTest extends AbstractTestNGSpringContextTests {

    @Test
    public void createScenario() {

        RestAssured.port = 8080;

    }

    @Test
    public void editScenario() {

        RestAssured.port = 8080;

    }

    @Test
    public void deleteScenario() {

        RestAssured.port = 8080;

    }

}
