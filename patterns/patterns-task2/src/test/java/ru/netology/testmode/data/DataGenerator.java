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
    private static Faker faker = new Faker(new Locale("en"));

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

    private static String getLogin() {
        val login = faker.name().username();
        return login;
    }

    private static String getPassword() {
        val password = faker.internet().password();
        return password;
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationDto getUser() {
            val user = new RegistrationDto(getLogin(), getPassword(), "");
            return user;
        }

        public static RegistrationDto getRegisteredUser(String status) {
            val registeredUser = new RegistrationDto(getLogin(), getPassword(), status);
            registerUser(registeredUser);
            return registeredUser;
        }

        public static RegistrationDto getWrongLoginUser(String status) {
            val password = getPassword();
            val registeredUser = new RegistrationDto(getLogin(), password, status);
            val wrongLoginUser = new RegistrationDto(getLogin(), password, status);
            registerUser(registeredUser);
            return wrongLoginUser;
        }

        public static RegistrationDto getWrongPasswordUser(String status) {
            val login = getLogin();
            val registeredUser = new RegistrationDto(login, getPassword(), status);
            val wrongPasswordUser = new RegistrationDto(login, getPassword(), status);
            registerUser(registeredUser);
            return wrongPasswordUser;
        }
    }

    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }
}
