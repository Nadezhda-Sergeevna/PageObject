package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology. web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        var authInfo = DataHelper.getAuthInfo();
        var firstCard = DataHelper.getFirstCard();
        var secondCard = DataHelper.getSecondCard();

        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo.getLogin(), authInfo.getPassword());
        var dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());

        int amount = 1000;
        int firstBalanceBefore = dashboardPage.getCardBalance("0001");
        int secondBalanceBefore = dashboardPage.getCardBalance("0002");

        var transferPage = dashboardPage.selectCardToTransfer("0001");
        dashboardPage = transferPage.transfer(secondCard.getNumber(), amount);

        assertEquals(firstBalanceBefore + amount, dashboardPage.getCardBalance("0001"));
        assertEquals(secondBalanceBefore - amount, dashboardPage.getCardBalance("0002"));
    }
}