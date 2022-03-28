package my.test.demo.alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JSAlertDemo {
    private final WebDriver driver;

    public JSAlertDemo(WebDriver driver) {
        this.driver = driver;
    }

    public String javaScriptAlertBox() {
        driver.navigate().to("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
        WebElement clickMeButton = driver.findElement(By.xpath("//button[@class='btn btn-default']"));
        clickMeButton.click();
        Alert alert = new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.alertIsPresent());
        String alertTextActual = alert.getText();
        return alertTextActual;
    }

}
