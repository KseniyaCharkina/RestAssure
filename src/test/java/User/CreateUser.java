package User;

import dto.User;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.UserApi;

import static org.hamcrest.Matchers.equalTo;

public class CreateUser {
    private UserApi userApi = new UserApi();

    //Добавляем пользователя и получаем его по userName
    @Test
    public void testCaseOne() {
        User userMax = User.builder().email("email")
                .firstName("Max")
                .id(1L)
                .lastName("Petrov")
                .password("Abcdfg")
                .phone("89054541221")
                .userStatus(132L)
                .username("Kot")
                .build();

        Response response = userApi.createUser(userMax);
        response.then()
                .log().all()
                .body("type", equalTo("unknown"))
                .body("message", equalTo("1"))
                .statusCode(HttpStatus.SC_OK);

        // String messegeExpected = response.jsonPath().get("message");
        //Assertions.assertEquals("1", messegeExpected);

        response = userApi.getUser(userMax.getUsername());
        response.then()
                .log().all();


    }

    //Получаем пользователя, которого нет.
    @Test
    public void testCaseTwo() {
        User userRita = User.builder().email("email@")
                .firstName("Rita")
                .id(2L)
                .lastName("Ivanova")
                .password("Abeeee")
                .phone("89054541225")
                .userStatus(136L)
                .username("Koshka")
                .build();

        Response response = userApi.getUser(userRita.getUsername());
        response.then()
                .log().all()
                .body("message", equalTo("User not found"))
                .statusCode(HttpStatus.SC_NOT_FOUND);


    }

    //Добавляем пользователя.Получаем, удаляем. Проверяем,что его нет.
    @Test
    public void testCaseThree() {
        User userMary = User.builder().email("email56")
                .firstName("Mary")
                .id(3L)
                .lastName("Ivanova")
                .password("KJGcdo")
                .phone("89054541225")
                .userStatus(137L)
                .username("Alenka")
                .build();


        Response response = userApi.createUser(userMary);
        response.then()
                .log().all()
                .body("type", equalTo("unknown"))
                .body("message", equalTo("3"))
                .statusCode(HttpStatus.SC_OK);

        response = userApi.getUser(userMary.getUsername());
        response.then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);


        response = userApi.deleteUser(userMary.getUsername());


        response = userApi.getUser(userMary.getUsername());
        response.then()
                .log().all()
                .body("message", equalTo("User not found"))
                .statusCode(HttpStatus.SC_NOT_FOUND);

    }


}
