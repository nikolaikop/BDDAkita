package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;
import ru.alfabank.alfatest.cucumber.annotations.Name;
import ru.alfabank.alfatest.cucumber.api.AkitaPage;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.page;


@Name("Страница входа")
public class LoginPage extends AkitaPage {

    @Name("Логин")
    @FindBy(css = "[data-test-id=login] input")
    public SelenideElement fieldLogin;
    @Name("Пароль")
    @FindBy(css = "[data-test-id=password] input")
    public SelenideElement fieldPw;
    @Name("Продолжить")
    @FindBy(css = "[data-test-id=action-login]")
    public SelenideElement loginButton;

    public VerificationPage validLogin(DataHelper.AuthInfo authInfo){
        fieldLogin.setValue(authInfo.getLogin());
        fieldPw.setValue(authInfo.getPassword());
        loginButton.click();
        return page(VerificationPage.class);
    }
}
