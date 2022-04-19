package my.test.auto;

import my.test.email.HomePage;
import my.test.email.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CreateAccountAutoPage {
    @FindBy(xpath = "//input[@id='customer_firstname']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@id='customer_lastname']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@name='address1']")
    private WebElement addressField;

    @FindBy(xpath = "//input[@id='city']")
    private WebElement cityField;

    @FindBy(xpath = "//select[@id='id_state']")
    private WebElement stateDropDown;

    @FindBy(xpath = "//input[@id='postcode']")
    private WebElement postalCodeField;

    @FindBy(xpath = "//input[@id='phone_mobile']")
    private WebElement phoneField;

    private final WebDriver driver;

    public CreateAccountAutoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }



    public CreateAccountAutoPage submitLogin() {
        submitLoginButton.submit();
        return this;
    }

    public CreateAccountAutoPage typePassword(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public HomePage submitPassword() {
        submitPasswordButton.submit();
        return new HomePage(driver);
    }

    public HomePage login(String username, String password) {
        driver.navigate().to(LOGIN_URL);
        openLoginForm();
        typeUsername(username);
        submitLogin();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(passwordField));
        typePassword(password);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(submitPasswordButton));
        return submitPassword();
    }

}
