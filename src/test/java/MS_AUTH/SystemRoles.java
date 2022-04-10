package MS_AUTH;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class SystemRoles extends BaseTest {
    @Test
    void GetAllRoles() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .and()
                .body("")
                .log()
                .all()
                .when()
                .get("http://"+host+":8762/api/v1/auth/roles?size=100")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
