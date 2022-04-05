package my.test.email;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    private static final String LOGIN_URL = "https://mail.yandex.com/";
    private static final String LOGIN_FIELD = "//input[@id='passp-field-login']";
    private static final String LOGIN_BUTTON = "//div[@class='HeadBanner-ButtonsWrapper']/a[.='Log in']";
    private static final String SUBMIT_LOGIN_BUTTON = "//button[@id='passp:sign-in']";
    private static final String PASSWORD_FIELD = "//input[@id='passp-field-passwd']";
    private static final String SUBMIT_PASSWORD_BUTTON = ".Button2_view_action";

    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage openLoginForm() {
        WebElement loginButton = driver.findElement(By.xpath(LOGIN_BUTTON));
        loginButton.click();
        return this;
    }

    public LoginPage typeUsername(String username) {
        driver.findElement(By.xpath(LOGIN_FIELD)).sendKeys(username);
        return this;
    }

    public LoginPage submitLogin() {
        driver.findElement(By.xpath(SUBMIT_LOGIN_BUTTON)).submit();
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(By.xpath(PASSWORD_FIELD)).sendKeys(password);
        return this;
    }

    public HomePage submitPassword() {
        driver.findElement(By.cssSelector(SUBMIT_PASSWORD_BUTTON)).submit();
        return new HomePage(driver);
    }

    public HomePage login(String username, String password) {
        driver.navigate().to(LOGIN_URL);
        openLoginForm();
        typeUsername(username);
        submitLogin();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath(PASSWORD_FIELD)));
        typePassword(password);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(SUBMIT_PASSWORD_BUTTON)));
        return submitPassword();
    }
}