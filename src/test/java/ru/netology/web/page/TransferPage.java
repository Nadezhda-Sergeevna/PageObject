package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class TransferPage {
    private SelenideElement amountInput = $("[data-test-id=amount] input");
    private SelenideElement fromInput = $("[data-test-id=from] input");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public DashboardPage transfer(String fromCardNumber, int amount) {
        amountInput.setValue(String.valueOf(amount));
        fromInput.setValue(fromCardNumber);
        transferButton.click();
        return new DashboardPage();
    }
}