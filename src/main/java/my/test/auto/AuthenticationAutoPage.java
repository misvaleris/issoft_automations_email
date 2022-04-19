package my.test.auto;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthenticationAutoPage {

    @FindBy(xpath = "//input[@id='email_create']")
    private WebElement emailRegistrationFiled;

    @FindBy(xpath = "//button[@name='SubmitCreate']")
    private WebElement createAccountButton;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement emailLoginField;

    @FindBy(xpath = "//input[@id='passwd']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@name='SubmitLogin']")
    private WebElement signInButton;

    private final WebDriver driver;
    private static final String LOGIN_URL = "http://automationpractice.com/index.php?controller=authentication&back=my-account";


    public AuthenticationAutoPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = Driver.getInstance().getChromeDriver();
    }

    public CreateAccountAutoPage submitRegistrationEmail(String email) {
        driver.get(LOGIN_URL);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(emailRegistrationFiled));
        emailRegistrationFiled.sendKeys(email);
        createAccountButton.click();
        return new CreateAccountAutoPage();
    }


    public HomeAutoPage login(String email, String password) {
        driver.get(LOGIN_URL);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(emailLoginField));
        emailLoginField.sendKeys(email);
        passwordField.sendKeys(password);
        signInButton.click();
        return new HomeAutoPage();
    }
}
