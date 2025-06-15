package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.Condition;

public class DashboardPage {
    private ElementsCollection cards = $$("ul.list li.list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        $("h2.heading").shouldHave(Condition.text("Личный кабинет"));
    }

    public int getCardBalance(String maskedCardNumber) {
        SelenideElement card = cards.findBy(Condition.text(maskedCardNumber));
        String text = card.text();
        return extractBalance(text);
    }

    public TransferPage selectCardToTransfer(String maskedCardNumber) {
        SelenideElement card = cards.findBy(Condition.text(maskedCardNumber));
        card.$("button").click();
        return new TransferPage();
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish).trim();
        return Integer.parseInt(value);
    }
}