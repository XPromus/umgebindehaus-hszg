package de.hszg.umgebindehaus.backend.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

@SpringBootTest (
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)

public class ScenarioControllerTest extends AbstractTestNGSpringContextTests {

    @Test
    public void createScenario() {

        RestAssured.port = 8080;
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("Name")
                .when()
                .post("/scenario/create")
                .body()
                .print();

        RestAssured.get("/scenario/name/Name").then().statusCode(200);

    }

    @Test
    public void editScenario() {

        RestAssured.port = 8080;

        var changes = "{" +
                "\"scenarioId\":3," +
                "\"newName\":\"New Name\"," +
                "\"newTime\":\"2020-07-30T10:15:30\"," +
                "\"newTimeScale\":2.0," +
                "\"newAutomaticWeather\":true," +
                "\"newAutomaticTime\":true," +
                "\"newWeatherWindDirection\":20.0," +
                "\"newWeatherWindSpeed\":5.0," +
                "\"newWeatherCloudiness\":\"CLEAR\"" +
                "}";

        var expectedBody = "{" +
                "\"id\":3," +
                "\"name\":\"New Name\"," +
                "\"time\":\"2020-07-30T10:15:30\"," +
                "\"weather\":{\"windDirection\":20.0,\"windSpeed\":5.0,\"cloudiness\":\"CLEAR\"}," +
                "\"timeScale\":2.0," +
                "\"automaticWeather\":true," +
                "\"automaticTime\":true}";

        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("Name")
                .when()
                .post("/scenario/create");

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(changes)
                .when()
                .post("/scenario/edit");

        response.then().statusCode(200);
        Assert.assertEquals(response.body().print(), expectedBody);

    }

    @Test
    public void deleteScenario() {

        RestAssured.port = 8080;
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("Name")
                .when()
                .post("/scenario/create");

        RestAssured.given().delete("/scenario/delete/3").then().statusCode(200);
        RestAssured.given().get("/scenario/id/3").then().statusCode(404);

    }

    @Test
    public void listAllScenarios() {

        RestAssured.port = 8080;
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("Scenario 1")
                .when()
                .post("/scenario/create");

        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("Scenario 2")
                .when()
                .post("/scenario/create");

        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("Scenario 3")
                .when()
                .post("/scenario/create");

        var response = RestAssured.given().get("/scenario/all");
        response.body().print();
        response.then().statusCode(200);

    }

    @Test
    public void getScenarioById() {

        var id = "3";

        RestAssured.port = 8080;
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("Test Scenario")
                .when()
                .post("/scenario/create");

        var response = RestAssured.given().get("/scenario/id/" + id);
        var responseBody = response.body().print();
        response.then().statusCode(200);

    }

}
