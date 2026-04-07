package ru.netology.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Keys;
import ru.netology.util.ScreenShooterReportPortalExtension;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static ru.netology.util.LoggingUtils.logInfo;
import static ru.netology.web.DataGenerator.*;

@ExtendWith({ScreenShooterReportPortalExtension.class})
public class OrderCardWithFakerTest {
    private final String city = getCity();
    private final String date = getDate(3);
    private final String dateOver = getDate(10);
    private final String name = getName();
    private final String phone = getPhone();

    @Test
    void shouldRegisterNewDate() {
        open("http://localhost:9999/");

        $("[data-test-id='city'] input").setValue(city);
        logInfo("В поле ввода города введено: " + city);

        $("[data-test-id='date'] input")
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(date);
        logInfo("В поле ввода даты введено: " + date);

        $("[data-test-id='name'] input").setValue(name);
        logInfo("В поле ввода имени введено: " + name);

        $("[data-test-id='phone'] input").setValue(phone);
        logInfo("В поле ввода номера телефона введено: " + phone);

        $("[data-test-id='agreement']").click();
        logInfo("Выполнен клик по чекбоксу ");

        $$("button").find(exactText("Запланировать")).click();
        $(".notification__title").shouldBe(visible, Duration.ofSeconds(12));
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + date));
        logInfo("Встреча успешно запланирована на " + date);

        $("[data-test-id='city'] input")
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(city);
        logInfo("В поле ввода города введено: " + city);

        $("[data-test-id='date'] input")
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(dateOver);
        logInfo("В поле ввода даты введено: " + dateOver);

        $("[data-test-id='name'] input")
                .press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE)
                .setValue(name);
        logInfo("В поле ввода имени введено: " + name);

        $("[data-test-id='phone'] input").setValue(phone);
        logInfo("В поле ввода номера телефона введено: " + phone);

        $$("button").find(exactText("Запланировать")).click();
        $("[data-test-id='replan-notification'] button").click();
        $("[data-test-id='success-notification'] .notification__content")
                .shouldHave(exactText("Встреча успешно запланирована на " + dateOver));
        logInfo("Встреча успешно запланирована на " + dateOver);
    }
}