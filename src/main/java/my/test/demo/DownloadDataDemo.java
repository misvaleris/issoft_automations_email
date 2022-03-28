package my.test.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class DownloadDataDemo {
    private final WebDriver driver;

    public DownloadDataDemo(WebDriver driver) {
        this.driver = driver;
    }

    public String downloadData() {
        driver.navigate().to("https://demo.seleniumeasy.com/bootstrap-download-progress-demo.html");
        WebElement downloadButton = driver.findElement(By.xpath("//button[@id='cricle-btn']"));
        downloadButton.click();

        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMinutes(1))
                .pollingEvery(Duration.ofSeconds(1))
                .ignoring(NoSuchElementException.class);

        wait.until(driver -> {
            WebElement percentageOfDownloading1 = driver.findElement(By.xpath("//div[@class='percenttext']"));

            String getTextOnPage = percentageOfDownloading1.getText();
            int indexId = getTextOnPage.indexOf("%");
            String modifiedMessage = getTextOnPage.replace(getTextOnPage.substring(indexId), "");
            int percentageValue = Integer.parseInt(modifiedMessage.trim());

            if (percentageValue >= 50){
                driver.navigate().refresh();
                return percentageOfDownloading1;
            } else {
                System.out.println("Percentage less than 50%");
                return null;
            }
        });

        WebElement percentageOfDownloadingValue = driver.findElement(By.xpath("//div[@class='percenttext']"));
        return percentageOfDownloadingValue.getText();
    }
}


