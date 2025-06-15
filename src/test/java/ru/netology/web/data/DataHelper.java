package ru.netology.web.data;

import lombok.Value;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DataHelper {
    private DataHelper() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String getVerificationCode() {
        return "12345";
    }

    public static CardInfo getFirstCard() {
        return new CardInfo("5559 0000 0000 0001", 0);
    }

    public static CardInfo getSecondCard() {
        return new CardInfo("5559 0000 0000 0002", 1);
    }

    public static String getCardLastDigits(String fullCardNumber) {
        return fullCardNumber.substring(fullCardNumber.length() - 4);
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class CardInfo {
        String number;
        int index;
    }
}
