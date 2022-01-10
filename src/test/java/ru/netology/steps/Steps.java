package ru.netology.steps;

import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import ru.alfabank.alfatest.cucumber.api.AkitaScenario;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardCardPage;
import ru.netology.page.DashboardCardRepPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static ru.alfabank.tests.core.helpers.PropertyLoader.loadProperty;

public class Steps {

    private final AkitaScenario scenario = AkitaScenario.getInstance();

    @Пусть("^пользователь залогинен с именем \"([^\"]*)\" и паролем \"([^\"]*)\";$")
    public void loginWithNameAndPassword(String login, String password) {
        // из .properties файла читаем свойство loginUrl
        val loginUrl = loadProperty("loginUrl");
        open(loginUrl);
        scenario.setCurrentPage(page(LoginPage.class));
        val loginPage = (LoginPage) scenario.getCurrentPage().appeared();
        val authInfo = new DataHelper.AuthInfo(login, password);
        scenario.setCurrentPage(loginPage.validLogin(authInfo));
        val verificationPage = (VerificationPage) scenario.getCurrentPage().appeared();
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        scenario.setCurrentPage(verificationPage.validVerify(verificationCode));
        scenario.getCurrentPage().appeared();
    }


    @Когда("^он переводит \"([^\"]*)\" рублей с карты с номером \"([^\"]*)\" на свою \"([^\"]*)\" карту со страницы перевода средств;$")
    public void transferMoneyFromSecondToFirstCard(String sumRep, String fromCard, String firstCard) {
        val dashboardPage = (DashboardCardPage) scenario.getCurrentPage().appeared();
        scenario.setCurrentPage(dashboardPage.depositToFirstCard());
        val transferPage = (DashboardCardRepPage) scenario.getCurrentPage().appeared();
        scenario.setCurrentPage(transferPage.refillCard(sumRep, fromCard));
        scenario.getCurrentPage().appeared();
    }


    @Тогда("^баланс его \"([^\"]*)\" карты из списка на главной странице должен стать \"([^\"]*)\" рублей\\.$")
    public void checkBalanceFirstCard(String firstCardNumber, String expectedBalance) {
        val dashboardPage = (DashboardCardPage) scenario.getCurrentPage().appeared();
        val firstCardBalance = dashboardPage.getCardBalance(DataHelper.getFirstCardInfo().getCardNumber());
        Assertions.assertEquals(expectedBalance.replace(" ", ""), String.valueOf(firstCardBalance));
    }
}
