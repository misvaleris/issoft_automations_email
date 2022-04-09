package my.test.demo.table;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DownloadData {
    private final WebDriver driver;

    public DownloadData(WebDriver driver) {
        this.driver = driver;
    }

    public void downloadData() {
        String siteUrl = "https://file-examples.com/index.php/sample-documents-download/";
        driver.navigate().to(siteUrl);

        WebElement selectDocxButton = driver.findElement(By.xpath("//a[contains(@href,'sample-doc-download')]"));
        WebElement selectXlsxButton = driver.findElement(By.xpath("//a[contains(@href,'sample-xls-download')]"));
        WebElement selectPdfButton = driver.findElement(By.xpath("//a[contains(@href,'sample-ppt-file')]"));

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(selectDocxButton));
        selectDocxButton.click();
        WebElement downloadDocxButton = driver.findElement(By.xpath("//a[contains(@href,'100kB.docx')]"));
        downloadDocxButton.click();

        //check if div with id="ad_position_box" exists
        //find close button
        //click it
        WebElement downloadXlsxButton = driver.findElement(By.xpath("//a[contains(@href,'XLSX_10.')]"));
        WebElement downloadPdfButton = driver.findElement(By.xpath("//a[contains(@href,'150kB')]"));
    }
}


