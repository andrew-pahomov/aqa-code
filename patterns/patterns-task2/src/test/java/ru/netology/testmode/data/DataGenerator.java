package ru.netology.testmode.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;
import lombok.val;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    private static void registerUser(RegistrationDto user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

    public static String getLogin() {

        return login;
    }

    public static String getPassword() {
        // TODO: добавить логику для объявления переменной password и задания её значения, для генерации можно
        // использовать faker
        return password;
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationDto getUser(String status) {
            // TODO: создать пользователя user с использованием методов getLogin(), getPassword()
            // и переданного параметра status
            return user;
        }

        public static RegistrationDto getRegisteredUser(String status) {
            // TODO: получить пользователя registeredUser вызвав метод getUser(status) и сохранить
            // его в registeredUser, выполнить регистрацию пользователя вызвав registerUser(registeredUser)
            return registeredUser;
        }
    }

    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }
}
