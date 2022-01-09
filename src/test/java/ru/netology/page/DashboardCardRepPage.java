package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;

import static com.codeborne.selenide.Selenide.page;

public class DashboardCardRepPage extends AkitaPage {

    @Name("Заголовок")
    @FindBy(css = "[data-test-id=dashboard]")
    private SelenideElement heading;

    @Name("Сумма перевода")
    @FindBy(css = "[data-test-id='amount'] input")
    private SelenideElement sum;

    @Name("Поле откуда")
    @FindBy(css = "[data-test-id='from'] input")
    private SelenideElement fromWhich;

    @Name("Пополнить")
    @FindBy(css = "[data-test-id='action-transfer']")
    private SelenideElement buttonRefill;

    public DashboardCardPage refillCard(String sumRep, String fromRep) {
        sum.setValue(sumRep);
        fromWhich.setValue(fromRep);
        buttonRefill.click();
        return page(DashboardCardPage.class);
    }
}
