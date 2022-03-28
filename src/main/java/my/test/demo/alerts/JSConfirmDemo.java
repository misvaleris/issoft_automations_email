package my.test.demo.alerts;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class JSConfirmDemo {
    private final WebDriver driver;

    public JSConfirmDemo(WebDriver driver) {
        this.driver = driver;
    }

    public boolean javaScriptConfirmBox() {
        driver.navigate().to("https://demo.seleniumeasy.com/javascript-alert-box-demo.html");
        WebElement clickMeButton = driver.findElement(By.cssSelector("button[onclick='myConfirmFunction()']"));
        clickMeButton.click();

        new WebDriverWait(driver, Duration.ofMinutes(1)).until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        String alertTextActual = alert.getText();
        alert.dismiss();

        WebElement confirmationText = driver.findElement(By.xpath("//p[@id='confirm-demo']"));
        String confirmationTextCancel = confirmationText.getText();

        clickMeButton.click();
        driver.switchTo().alert();
        alert.accept();
        String confirmationTextOk = confirmationText.getText();

        return alertTextActual.equals("Press a button!") & confirmationTextOk.equals("You pressed OK!") & confirmationTextCancel.equals("You pressed Cancel!");
    }
}
