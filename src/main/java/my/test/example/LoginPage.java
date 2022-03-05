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

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='Compose']")));
        WebElement createEmailButton = driver.findElement(By.xpath("//a[.='Compose']"));
        return createEmailButton.isDisplayed();
    }
}
