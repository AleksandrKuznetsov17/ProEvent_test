package MS_AUTH;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class User extends BaseTest {

    @Test
    void GetUsers(){
        Response response = given()
                .header("Authorization", "Bearer " + loadedToken)
                .body("")
                .log()
                .all()
                .when()
                .get("http://"+host+":8762/api/v1/auth/users?size=100")
                .prettyPeek();
        assertThat(response.jsonPath().get("content"), is(not(empty())));
    }

    @Test
    void DeleteOwnAccount() {
        given ()
                .header("Authorization", "Bearer " + loadedToken)
                .body("")
                .log()
                .all()
                .when()
                .delete("http://"+host+":8762/api/v1/auth/users")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void DeleteUserById() {
        given ()
                .header("Authorization", "Bearer " + loadedToken)
                .body("")
                .log()
                .all()
                .when()
                .delete("http://"+host+":8762/api/v1/auth/users/77")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void AddGroupToUser() {
        given ()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("[\\n  3, 2, 1, 33\\n]")
                .log()
                .all()
                .when()
                .post("http://"+host+":8762/api/v1/auth/users/1/addGroups")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void AddRolesToUser() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("[\n  \"ROLE_MS_AUTH_USER_DEL\"\n]")
                .log()
                .all()
                .when()
                .post("http://"+host+":8762/api/v1/auth/users/1/addRoles")
                .prettyPeek()
                .then()
                .statusCode(200);

    }

    @Test
    void ChangeUserActive() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .body("")
                .log()
                .all()
                .when()
                .put("http://"+host+":8762/api/v1/auth/users/2/changeActive")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void FictionDeleteUserById() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .body("")
                .log()
                .all()
                .when()
                .put("http://"+host+":8762/api/v1/auth/users/36/delete")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void DeleteGroupFromUser() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("[\\n  3, 1\\n]")
                .log()
                .all()
                .when()
                .delete("http://"+host+":8762/api/v1/auth/users/1/delGroups")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void DeleteRoleFromUser() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("[\n  \"ROLE_MS_PROFILE_UPDATE\"\n]")
                .log()
                .all()
                .when()
                .delete("http://"+host+":8762/api/v1/auth/users/1/delRoles")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void FullChangeGroupsUser() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("[\r\n    0\r\n]")
                .log()
                .all()
                .when()
                .put("http://"+host+":8762/ms-auth/api/v1/auth/users/1/updateGroups")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void FullChangeRolesUser() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("[\\r\\n  \\\"string\\\"\\r\\n]")
                .log()
                .all()
                .when()
                .put("http://"+host+":8762/ms-auth/api/v1/auth/users/1/updateRoles")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void UpdatePassword() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("{\\r\\n    \\\"email\\\": \\\"wilderwein17@yandex.ru\\\",\\r\\n    \\\"newPassword\\\": \\\"QAZxsw123\\\",\\r\\n    \\\"oldPassword\\\": \\\"QAZxsw1234\\\" \\r\\n}")
                .log()
                .all()
                .when()
                .put("http://"+host+":8762/ms-auth/api/v1/auth/users/updatePassword")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

}
