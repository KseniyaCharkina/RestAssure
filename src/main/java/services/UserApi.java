package services;

import dto.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class UserApi {
    private static final String BASE_URL = "https://petstore.swagger.io/v2/";
    private RequestSpecification spec;
    private static final String USER = "/user";
    private static final String GETUSER = "/user/";

    public UserApi() {
        spec = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);
    }

    public Response createUser(User user) {
        return
                given(spec)
                        .with()
                        .body(user)
                        .log().all()
                        .when()
                        .post(USER);

    }

    public Response getUser(String name) {
        return
                given(spec)
                        .with()
                        .log().all()
                        .when()
                        .get(GETUSER + name);
    }

    public Response deleteUser(String name) {
        return
                given(spec)
                        .with()
                        .log().all()
                        .when()
                        .delete(GETUSER + name);
    }
}
