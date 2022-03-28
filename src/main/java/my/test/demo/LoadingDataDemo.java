package my.test.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoadingDataDemo {

    private final WebDriver driver;

    public LoadingDataDemo(WebDriver driver) {
        this.driver = driver;
    }

    public boolean waitForUser() {
        driver.navigate().to("https://demo.seleniumeasy.com/dynamic-data-loading-demo.html");
        WebElement getNewUserButton = driver.findElement(By.cssSelector("//button[@id='save']"));
        getNewUserButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='loading']/img")));
        WebElement userData = driver.findElement(By.xpath("//div[@id='loading']/img"));
        return userData.isDisplayed();
    }
}
