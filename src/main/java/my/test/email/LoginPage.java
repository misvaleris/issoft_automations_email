package my.test.email;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class LoginPage {

    @FindBy(xpath = "//div[@class='HeadBanner-ButtonsWrapper']/a[.='Log in']")
    private WebElement loginButton;

    @FindBy(xpath = "//button[@id='passp:sign-in']")
    private WebElement submitLoginButton;

    @FindBy(css = ".Button2_view_action")
    private WebElement submitPasswordButton;

    @FindBy(xpath = "//a[@href='https://passport.yandex.com']//img[@src='https://avatars.mds.yandex.net/get-yapic/0/0-0/islands-middle']")
    private WebElement profileButton;

    @FindBy(css = ".user-account_has-subname_yes > .user-account__name")
    private WebElement userName;

    WebDriver driver = CreateDriver.getInstance();

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean login(String login, String password) {

        driver.navigate().to("https://mail.yandex.com/");
        takeSnapShot(driver, "/Users/misvaleris/Documents/test.png");

        loginButton.click();
        String loginField = "//input[@id='passp-field-login']";

        driver.findElement(By.xpath(loginField)).sendKeys(login);
        submitLoginButton.click();
        String passwordField = "//input[@id='passp-field-passwd']";

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement(By.xpath(passwordField)).sendKeys(password);

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".Button2_view_action")));
        submitPasswordButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://passport.yandex.com']//img[@src='https://avatars.mds.yandex.net/get-yapic/0/0-0/islands-middle']")));
        profileButton.click();

        new WebDriverWait(driver, Duration.ofMinutes(1).plusSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".user-account_has-subname_yes > .user-account__name")));
        return userName.isDisplayed();
    }

    public static void takeSnapShot(WebDriver webdriver, String fileWithPath) {
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            File DestFile = new File(fileWithPath);
            FileUtils.copyFile(SrcFile, DestFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

