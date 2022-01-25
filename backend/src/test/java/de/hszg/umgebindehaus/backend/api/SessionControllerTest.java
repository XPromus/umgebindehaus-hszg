package de.hszg.umgebindehaus.backend.api;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;

@SpringBootTest (
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)

public class SessionControllerTest extends AbstractTestNGSpringContextTests {

    @Test
    public void createSession() {

        RestAssured.port = 8080;
        var request = RestAssured.given()
                .when()
                .get("/session/create");

        var sessionID = request.body().print();
        request.then().statusCode(200);

        var getNewScenario = RestAssured.given()
                .when()
                .body(sessionID)
                .get("session/get/" + sessionID);

        getNewScenario.body().print();
        getNewScenario.then().statusCode(200);

    }

    @Test
    public void editSession() {

        RestAssured.port = 8080;
        var createResponse = RestAssured.given()
                .when()
                .get("/session/create");

        final String sessionID = createResponse.body().print();

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
                "\"id\":" + "\"" + sessionID + "\"" + "," +
                "\"name\":\"New Name\"," +
                "\"time\":\"2020-07-30T10:15:30\"," +
                "\"weather\":{\"windDirection\":20.0,\"windSpeed\":5.0,\"cloudiness\":\"CLEAR\"}," +
                "\"timeScale\":2.0," +
                "\"automaticWeather\":true," +
                "\"automaticTime\":true}";

        var editResponse = RestAssured.given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(changes)
                .when()
                .post("/session/edit/" + sessionID);

        editResponse.then().statusCode(200);

        var getResponse = RestAssured.given()
                .body(sessionID)
                .when()
                .get("/session/get/" + sessionID);

        Assert.assertEquals(getResponse.body().print(), expectedBody);

    }

    @Test
    public void getSessionData() {

        RestAssured.port = 8080;
        var createResponse = RestAssured.given()
                .when()
                .get("/session/create");

        var sessionID = createResponse.body().asString();

        var getResponse = RestAssured.given()
                .body(sessionID)
                .when()
                .get("/session/get/" + sessionID);

        getResponse.then().statusCode(200);

        JSONObject expectedBodyJSON = new JSONObject();
        JSONObject expectedWeatherJSON = new JSONObject();

        expectedWeatherJSON.put("windDirection", 0.0);
        expectedWeatherJSON.put("windSpeed", 0.0);
        expectedWeatherJSON.put("cloudiness", "CLEAR");

        expectedBodyJSON.put("id", sessionID);
        expectedBodyJSON.put("name", "");
        expectedBodyJSON.put("weather", expectedWeatherJSON);
        expectedBodyJSON.put("timeScale", 1.0);
        expectedBodyJSON.put("automaticWeather", true);
        expectedBodyJSON.put("automaticTime", true);

        JSONObject bodyJSON;

        try {
            bodyJSON = (JSONObject) new JSONParser(JSONParser.DEFAULT_PERMISSIVE_MODE).parse(getResponse.body().asString());
            bodyJSON.remove("time");
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        Assert.assertEquals(bodyJSON, expectedBodyJSON);

    }

    @Test
    public void closeSession() {

        RestAssured.port = 8080;
        var createResponse = RestAssured.given()
                .when()
                .get("/session/create");

        final String sessionID = createResponse.body().asString();

        var deleteResponse = RestAssured.given()
                .when()
                .body(sessionID)
                .when()
                .delete("/session/delete/" + sessionID);

        deleteResponse.then().statusCode(200);

        var getResponse = RestAssured.given()
                .when()
                .get("/session/get/" + sessionID);

        getResponse.then().statusCode(404);

    }

}
