package my.test.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

    WebDriver driver = new ChromeDriver();

    public void login(String login, String password) {

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
    }


    public boolean emailPageIsPresent() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Compose']")));
        WebElement createEmailButton = driver.findElement(By.xpath("//a[.='Compose']"));
        return createEmailButton.isDisplayed();
    }

    public void logOutCurrentUser() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://passport.yandex.com']//img[@src='https://avatars.mds.yandex.net/get-yapic/0/0-0/islands-middle']")));
        WebElement profileButton = driver.findElement(By.xpath("//a[@href='https://passport.yandex.com']//img[@src='https://avatars.mds.yandex.net/get-yapic/0/0-0/islands-middle']"));
        profileButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//ul[@class='menu__group']//a[.='Log out']")));
        WebElement logOutButton = driver.findElement(By.xpath("//ul[@class='menu__group']//a[.='Log out']"));
        logOutButton.click();
    }
}
