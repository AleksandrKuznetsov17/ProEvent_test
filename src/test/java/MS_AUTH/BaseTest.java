package MS_AUTH;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public abstract class BaseTest {

    static Properties properties = new Properties();
    static String login;
    static String password;
    static String host;
    static String token;
    static String loadedToken;


    @BeforeAll
    static void beforeAll() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        getProperties();
        login = properties.getProperty("login");
        password = properties.getProperty("password");
        host = properties.getProperty("host");
        token = properties.getProperty("token");

    }

    // POST Login
    @BeforeAll
    static void getToken() {

        loadedToken = given()
                .header("Content-Type", "application/json")
                .and()
                .body("{\n    \"email\": \""+login+"\",\n    \"password\": \""+password+"\"\n}")
                .when()
                .post("http://"+host+":8762/api/v1/auth/login")
                .then()
                .extract()
                .response()
                .jsonPath()
                .getString("token");
    }


    private static void getProperties() {
        try {
            properties.load(new FileInputStream("src/test/resources/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
