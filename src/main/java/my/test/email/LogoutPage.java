package my.test.email;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {
    private final WebDriver driver;

    public LogoutPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean logout() {

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Log out']")));
        WebElement profileButton = driver.findElement(By.xpath("//span[text()='Log out']"));
        profileButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//h1[@class='passp-title ']")));
        WebElement ChooseAnAccountTitle = driver.findElement(By.xpath("//h1[@class='passp-title ']"));
        ChooseAnAccountTitle.isDisplayed();

        return ChooseAnAccountTitle.isDisplayed();
    }
}
