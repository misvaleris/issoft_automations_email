package my.test.email;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogoutPage {
    private static final By LOGOUT_PAGE_TITLE = By.xpath("//div[@class='yaIdPromoText']");

    private final RemoteWebDriver driver;

    public LogoutPage() {
        this.driver = CreateDriver.getInstance().getEdgeDriver();
    }

    public String logout() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(LOGOUT_PAGE_TITLE));
        return driver.findElement(LOGOUT_PAGE_TITLE).getText();
    }
}
