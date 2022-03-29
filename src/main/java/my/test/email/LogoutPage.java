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

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://passport.yandex.com']//img[@src='https://avatars.mds.yandex.net/get-yapic/0/0-0/islands-middle']")));
        WebElement profileButton = driver.findElement(By.xpath("//a[@href='https://passport.yandex.com']//img[@src='https://avatars.mds.yandex.net/get-yapic/0/0-0/islands-middle']"));
        profileButton.click();

        WebElement logoutButton = driver.findElement(By.xpath("//li[@class='menu__list-item']/a[@aria-label='Log out']"));
        logoutButton.click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='text']")));
        WebElement searchBox = driver.findElement(By.xpath("//input[@id='text']"));
        searchBox.isDisplayed();

        return searchBox.isDisplayed();
    }
}
