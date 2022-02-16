package de.hszg.umgebindehaus.backend.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest (
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)

public class ScenarioControllerTest extends AbstractTestNGSpringContextTests {

    @Test
    public void createScenario() {

        var name = "Name";

        RestAssured.port = 8080;
        RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(name)
                .when()
                .post("/scenario/create")
                .body()
                .print();

        RestAssured.get("/scenario/name/" + name).then().statusCode(200);

    }

    @Test
    public void editScenario() throws JSONException {

        RestAssured.port = 8080;

        var createRequest = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("Name")
                .when()
                .post("/scenario/create");

        var jsonObject = new JSONObject(createRequest.body().asString());
        var id = jsonObject.get("id");

        var changes = "{" +
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
                "\"id\":" + id + "," +
                "\"name\":\"New Name\"," +
                "\"time\":\"2020-07-30T10:15:30\"," +
                "\"weather\":{\"windDirection\":20.0,\"windSpeed\":5.0,\"cloudiness\":\"CLEAR\"}," +
                "\"timeScale\":2.0," +
                "\"automaticWeather\":true," +
                "\"automaticTime\":true}";

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(changes)
                .when()
                .post("/scenario/edit/" + id);

        response.then().statusCode(200);
        Assert.assertEquals(response.body().asString(), expectedBody);

    }

    @Test
    public void deleteScenario() throws JSONException {

        RestAssured.port = 8080;
        var request = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("Name")
                .when()
                .post("/scenario/create");

        var jsonObject = new JSONObject(request.body().asString());
        var id = jsonObject.get("id");

        RestAssured.given().delete("/scenario/delete/" + id).then().statusCode(200);
        RestAssured.given().get("/scenario/id/" + id).then().statusCode(404);

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
    public void getScenarioById() throws JSONException {

        RestAssured.port = 8080;
        var request = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body("Test Scenario")
                .when()
                .post("/scenario/create");

        var jsonObject = new JSONObject(request.body().asString());
        var id = jsonObject.get("id");
        var response = RestAssured.given().get("/scenario/id/" + id);
        response.then().statusCode(200);

    }

}
