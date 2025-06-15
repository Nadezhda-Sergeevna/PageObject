package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.*;

public class TransferPage {
    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement fromInput = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");
    private SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public DashboardPage transferValid(String fromCardNumber, int amount) {
        amountInput.setValue(String.valueOf(amount));
        fromInput.setValue(fromCardNumber);
        transferButton.click();
        return new DashboardPage();
    }

    public TransferPage transferInvalid(String fromCardNumber, int amount) {
        amountInput.setValue(String.valueOf(amount));
        fromInput.setValue(fromCardNumber);
        transferButton.click();
        return this;
    }

    public void shouldShowError(String expectedText) {
        errorNotification
                .shouldBe(Condition.visible)
                .shouldHave(Condition.text(expectedText));
    }
}