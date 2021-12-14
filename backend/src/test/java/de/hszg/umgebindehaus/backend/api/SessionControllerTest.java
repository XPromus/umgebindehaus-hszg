package de.hszg.umgebindehaus.backend.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

@SpringBootTest (
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)

public class SessionControllerTest extends AbstractTestNGSpringContextTests {

    @Test
    public void createSession() {

        RestAssured.port = 8080;

    }

    @Test
    public void editSession() {

        RestAssured.port = 8080;

    }

    @Test
    public void getSessionData() {

        RestAssured.port = 8080;

    }

    @Test
    public void closeSession() {

        RestAssured.port = 8080;

    }

}
