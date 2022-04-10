package MS_AUTH;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Group extends BaseTest {

    @Test
    void GetAllGroups() {
        Response response = given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("")
                .log()
                .all()
                .when()
                .get("http://"+host+":8762/api/v1/auth/groups?size=100")
                .prettyPeek();
        assertThat(response.jsonPath().get("content"), is(not(empty())));
    }

    @Test
    void AddNewGroup() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("{\n  \"description\": \"Тестовая группа 3\",\n  \"groupName\": \"TEST_ROLE_GROUP3\"\n}")
                .log()
                .all()
                .when()
                .post("http:"+host+":8762/api/v1/auth/groups")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void UpdateGroup(){
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("{\n  \"id\": 3,\n  \"groupName\": \"GROUP_3\",\n  \"description\": \"Тестовая группа 3\",\n  \"roles\": [\n        {\n            \"roleName\": \"ROLE_MS_AUTH_ROLES_VIEW\"\n        },\n        {\n            \"roleName\": \"ROLE_MS_AUTH_GROUPS_VIEW\"\n        },\n        {\n            \"roleName\": \"ROLE_MS_AUTH_GROUP_INFO_VIEW\"\n        }\n    ]\n}")
                .log()
                .all()
                .when()
                .put("http:"+host+":8762/api/v1/auth/groups")
                .prettyPeek()
                .then()
                .statusCode(200);
}

    @Test
    void AddRolesToGroup() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("[\\n  {\\n    \\\"roleName\\\": \\\"ROLE_MS_PROFILE_VIEW\\\"\\n  },\\n  {\\n    \\\"roleName\\\": \\\"ROLE_MS_AUTH_ROLES_VIEW\\\"\\n  }\\n]")
                .log()
                .all()
                .when()
                .post("http:"+host+":8762/api/v1/auth/groups/3")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void DelRolesFromGroup() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .header("Content-Type", "application/json")
                .body("[\n  {\n    \"roleName\": \"ROLE_MS_PROFILE_VIEW\"\n  }\n]")
                .log()
                .all()
                .when()
                .post("http:"+host+":8762/api/v1/auth/groups/3")
                .prettyPeek()
                .then()
                .statusCode(200);
    }

    @Test
    void GetGroupInfo() {
        given()
                .header("Authorization", "Bearer " + loadedToken)
                .body("")
                .log()
                .all()
                .when()
                .get("http://178.249.69.107:8762/api/v1/auth/groups/1")
                .prettyPeek()
                .then()
                .statusCode(200);
    }
}
