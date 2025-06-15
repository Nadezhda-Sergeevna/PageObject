package ru.netology.web.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {
    private DashboardPage dashboardPage;
    private DataHelper.CardInfo firstCard;
    private DataHelper.CardInfo secondCard;

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
        var authInfo = DataHelper.getAuthInfo();
        firstCard = DataHelper.getFirstCard();
        secondCard = DataHelper.getSecondCard();
        var loginPage = new LoginPage();
        var verificationPage = loginPage.validLogin(authInfo.getLogin(), authInfo.getPassword());
        dashboardPage = verificationPage.validVerify(DataHelper.getVerificationCode());
    }

    @Test
    void shouldTransferMoneyFromSecondToFirstCard() {
        var firstId = DataHelper.getCardLastDigits(firstCard.getNumber());
        var secondId = DataHelper.getCardLastDigits(secondCard.getNumber());

        int amount = dashboardPage.getCardBalance(secondId) / 2;
        int firstBefore = dashboardPage.getCardBalance(firstId);
        int secondBefore = dashboardPage.getCardBalance(secondId);

        dashboardPage = dashboardPage
                .selectCardToTransfer(firstId)
                .transferValid(secondCard.getNumber(), amount);

        assertEquals(firstBefore + amount, dashboardPage.getCardBalance(firstId));
        assertEquals(secondBefore - amount, dashboardPage.getCardBalance(secondId));
    }

    @Test
    void shouldShowErrorIfInsufficientFunds() {
        var firstId = DataHelper.getCardLastDigits(firstCard.getNumber());
        int invalidAmount = dashboardPage.getCardBalance(DataHelper.getCardLastDigits(secondCard.getNumber())) + 1;

        dashboardPage.selectCardToTransfer(firstId)
                .transferInvalid(secondCard.getNumber(), invalidAmount)
                .shouldShowError("Ошибка");
    }
}
