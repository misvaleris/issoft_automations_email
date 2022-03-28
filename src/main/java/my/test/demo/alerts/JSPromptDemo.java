package my.test.demo.alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JSPromptDemo {
    private final WebDriver driver;

    public JSPromptDemo(WebDriver driver) {
        this.driver = driver;
    }

    public boolean javaScriptPromptBox() {
        driver.navigate().to("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
        WebElement clickMeButton = driver.findElement(By.cssSelector("button[onclick='myPromptFunction()']"));
        clickMeButton.click();

        Alert alert = new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.alertIsPresent());
        alert.sendKeys("Selenium");
        alert.accept();

        WebElement enteredText = driver.findElement(By.xpath("//p[@id='prompt-demo']"));
        String confirmationEnteredText = enteredText.getText();

        clickMeButton.click();
        driver.switchTo().alert();
        alert.dismiss();
        String confirmationNotEnteredText = enteredText.getText();

        return confirmationEnteredText.contains("Selenium") & confirmationNotEnteredText.contains("Selenium");
    }
}
