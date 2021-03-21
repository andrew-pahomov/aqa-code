package ru.netology.delivery.data;

import com.github.javafaker.Faker;
import lombok.Value;
import lombok.val;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateDate(int shift) {
        val date = LocalDate.now().plusDays(shift).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        return date;
    }

    private static String generateCity(Faker faker) {
        val random = new Random();
        val cities = new String[]{"Москва", "Воронеж", "Краснодар", "Владивосток"};
        val city = cities[random.nextInt(cities.length)];
        return city;
    }

    private static String generateName(Faker faker) {
        val name = faker.name().lastName() + " " + faker.name().firstName();
        return name;
    }

    private static String generatePhone(Faker faker) {
        val phone = faker.phoneNumber().phoneNumber();
        return phone;
    }

    public static class Registration {
        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            val faker = new Faker(new Locale(locale));
            return new UserInfo(generateCity(faker), generateName(faker), generatePhone(faker));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
