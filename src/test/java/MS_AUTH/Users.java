package MS_AUTH;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

class Users extends BaseTest {

    @Test
    void LoginSuperAdmin() {
        given()
                .header("Content-Type", "application/json")
                .and()
                .body("{\n    \"email\": \""+login+"\",\n    \"password\": \""+password+"\"\n}")
                .log()
                .all()
                .when()
                .post("http://"+host+":8762/api/v1/auth/login")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void LoginTestUser() {
        given()
                .header("Content-Type", "application/json")
                .and()
                .body("{\n    \"email\": \""+login+"\",\n    \"password\": \""+password+"\"\n}")
                .log()
                .all()
                .when()
                .post("http://"+host+":8762/api/v1/auth/login")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void Signup() {
        given()
                .header("Content-Type", "application/json")
                .and()
                .body("{\n    \"email\": \"alexander18.87@mail.ru\",\n    \"password\": \"qazxsw123\",\n    \"agreement\": true\n}")
                .log()
                .all()
                .when()
                .post("http://"+host+":8762/api/v1/auth/signup")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void VerificationCheckCode(){
        given()
                .header("Content-Type", "application/json")
                .and()
                .body("{\n    \"email\": \"alexander18.87@mail.ru\",\n    \"code\": 5744\n}")
                .log()
                .all()
                .when()
                .post("http://"+host+":8762/api/v1/auth/verificationCheckCode")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void ResetPassword() {
        Response response = given()
                .header("Content-Type", "application/json")
                .and()
                .body("{\n    \"email\": \"alexander18.87@mail.ru\"\n}")
                .log()
                .all()
                .when()
                .post("http://"+host+":8762/api/v1/auth/resetPassword")
                .prettyPeek();

    }

    @Test
    void CreateNewPassword() {
        Response response = given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .and()
                .body("{\r\n    \"email\": \"alexander18.87@mail.ru\",\r\n    \"code\": \"7939\",\r\n    \"password\": \"QAZxsw123\"\r\n}")
                .log()
                .all()
                .when()
                .post("http://"+host+":8762/api/v1/auth/newPassword")
                .prettyPeek();
    }

    @Test
    void RefreshCheckCode() {
        given()
                .header("Content-Type", "application/json")
                .and()
                .body("{\r\n  \"email\": \"akuznetsov87.ak@gmail.com\"\r\n}")
                .log()
                .all()
                .when()
                .post("http://"+host+":8762/api/v1/auth/refreshCheckCode")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void AddUserByEmail() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("")
                .log()
                .all()
                .when()
                .get("http://"+host+":8762/api/v1/auth/invite?email=MelnikovNB@yandex.ru")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

}
