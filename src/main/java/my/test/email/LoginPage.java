package my.test.email;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver = CreateDriver.getInstance();

    public boolean login(String login, String password) {

        driver.navigate().to("https://mail.yandex.com/");

        WebElement loginButton = driver.findElement(By.xpath("//div[@class='HeadBanner-ButtonsWrapper']/a[.='Log in']"));
        loginButton.click();
        String loginField = "//input[@id='passp-field-login']";

        driver.findElement(By.xpath(loginField)).sendKeys(login);
        WebElement submitLoginButton = driver.findElement(By.xpath("//button[@id='passp:sign-in']"));
        submitLoginButton.click();
        String passwordField = "//input[@id='passp-field-passwd']";

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='passp-field-passwd']")));
        driver.findElement(By.xpath(passwordField)).sendKeys(password);

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".Button2_view_action")));
        WebElement submitPasswordButton = driver.findElement(By.cssSelector(".Button2_view_action"));
        submitPasswordButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://passport.yandex.com']//img[@src='https://avatars.mds.yandex.net/get-yapic/0/0-0/islands-middle']")));
        WebElement profileButton = driver.findElement(By.xpath("//a[@href='https://passport.yandex.com']//img[@src='https://avatars.mds.yandex.net/get-yapic/0/0-0/islands-middle']"));
        profileButton.click();

        new WebDriverWait(driver, Duration.ofMinutes(1).plusSeconds(1)).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".user-account_has-subname_yes > .user-account__name")));
        WebElement userName = driver.findElement(By.cssSelector(".user-account_has-subname_yes > .user-account__name"));

        return userName.isDisplayed();
    }

}

