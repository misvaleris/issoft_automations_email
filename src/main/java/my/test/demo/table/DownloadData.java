package my.test.demo.table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DownloadData {
    private final WebDriver driver;

    public DownloadData(WebDriver driver) {
        this.driver = driver;
    }

    private static final By SELECT_DOCX_BUTTON_XPATH = By.xpath("//a[contains(@href,'sample-doc-download')]");
    private static final By SELECT_XLSX_BUTTON_XPATH = By.xpath("//a[contains(@href,'sample-xls-download')]");
    private static final By SELECT_PDF_BUTTON_XPATH = By.xpath("//a[contains(@href,'sample-ppt-file')]");

    private static final By DOWNLOAD_DOCX_BUTTON_XPATH = By.xpath("//a[contains(@href,'100kB.docx')]");
    private static final By DOWNLOAD_XLSX_BUTTON_XPATH = By.xpath("//a[contains(@href,'XLSX_10.')]");
    private static final By DOWNLOAD_PDF_BUTTON_XPATH = By.xpath("//a[contains(@href,'150kB')]");

    private static final By DOWNLOADING_TITLE_XPATH = By.xpath("//em[text()='Please wait a moment']");



    public void downloadData() {

        String siteUrl = "https://file-examples.com/index.php/sample-documents-download/";
        driver.navigate().to(siteUrl);

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(SELECT_DOCX_BUTTON_XPATH));
        driver.findElement(SELECT_DOCX_BUTTON_XPATH).click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(DOWNLOAD_DOCX_BUTTON_XPATH));
        driver.findElement(DOWNLOAD_DOCX_BUTTON_XPATH).click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(DOWNLOADING_TITLE_XPATH));
        driver.findElement(DOWNLOADING_TITLE_XPATH).isDisplayed();

        driver.navigate().to(siteUrl);

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(SELECT_XLSX_BUTTON_XPATH));
        driver.findElement(SELECT_XLSX_BUTTON_XPATH).click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(DOWNLOAD_XLSX_BUTTON_XPATH));
        driver.findElement(DOWNLOAD_XLSX_BUTTON_XPATH).click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(DOWNLOADING_TITLE_XPATH));
        driver.findElement(DOWNLOADING_TITLE_XPATH).isDisplayed();

        driver.navigate().to(siteUrl);

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(SELECT_PDF_BUTTON_XPATH));
        driver.findElement(SELECT_PDF_BUTTON_XPATH).click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(DOWNLOAD_PDF_BUTTON_XPATH));
        driver.findElement(DOWNLOAD_PDF_BUTTON_XPATH).click();

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(DOWNLOADING_TITLE_XPATH));
        driver.findElement(DOWNLOADING_TITLE_XPATH).isDisplayed();



        //check if div with id="ad_position_box" exists
        //find close button
        //click it

    }
}


