package MS_AUTH;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class FirebaseTokens extends BaseTest {

    @Test
    void SendFirebaseMessage() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .and()
                .body("{\r\n    \"receiverId\": 96,\r\n    \"title\": \"Attention!!!\",\r\n    \"text\": \"Message text\",\r\n    \"imageUrl\": \"http://link-to-image.com/\",\r\n    \"data\": {\r\n        \"param1\": \"value1\",\r\n        \"param2\": \"value2\"\r\n    }\r\n}\r\n\r\n")
                .log()
                .all()
                .when()
                .post("http://"+host+":8762/api/v1/auth/firebase/send")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    // в разработке
    @Test
    void DeleteFirebaseToken() {
       given()
               .header("Authorization", "Bearer " + loadedToken)
               .header("Content-Type", "application/json")
               .and()
               .body("")
               .log()
               .all()
               .when()
               .delete("http://178.249.69.107:8762/api/v1/auth/firebase")
               .prettyPeek()
               .then()
               .statusCode(200);
    }

    // в разработке
    @Test
    void FirebaseRegistration() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .and()
                .body("{\r\n  \"token\": \"string\"\r\n}")
                .log()
                .all()
                .when()
                .post("http://178.249.69.107:8762/api/v1/auth/firebase")
                .prettyPeek()
                .then()
                .statusCode(200);
    }



}
