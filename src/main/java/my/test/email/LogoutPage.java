package my.test.email;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {
    private static final String LOGOUT_PAGE_TITLE = "//div[@class='yaIdPromoText']";

    private final WebDriver driver;

    public LogoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public String logout() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath(LOGOUT_PAGE_TITLE)));
        return driver.findElement(By.xpath(LOGOUT_PAGE_TITLE)).getText();
    }
}
