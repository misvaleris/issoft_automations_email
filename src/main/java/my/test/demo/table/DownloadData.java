package my.test.demo.table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class DownloadData {
    private final WebDriver driver;

    public DownloadData(WebDriver driver) {
        this.driver = driver;
    }

    private static final By SELECT_DOCX_BUTTON_XPATH = By.xpath("//a[contains(@href,'sample-doc-download')]");
    private static final By SELECT_XLSX_BUTTON_XPATH = By.xpath("//a[contains(@href,'sample-xls-download')]");
    private static final By SELECT_PDF_BUTTON_XPATH = By.xpath("//a[contains(@href,'sample-pdf-download')]");

    private static final By DOWNLOAD_DOCX_BUTTON_XPATH = By.xpath("//a[contains(@href,'100kB.docx')]");
    private static final By DOWNLOAD_XLSX_BUTTON_XPATH = By.xpath("//a[contains(@href,'XLSX_10.')]");
    private static final By DOWNLOAD_PDF_BUTTON_XPATH = By.xpath("//a[contains(@href,'150kB')]");

    private static final By DOWNLOADING_TITLE_XPATH = By.xpath("//em[text()='Please wait a moment']");
    private static final String siteUrl = "https://file-examples.com/index.php/sample-documents-download/";

    public String downloadData() {

        Map<By, By> map = new HashMap<>();
        map.put(SELECT_DOCX_BUTTON_XPATH, DOWNLOAD_DOCX_BUTTON_XPATH);
        map.put(SELECT_XLSX_BUTTON_XPATH, DOWNLOAD_XLSX_BUTTON_XPATH);
        map.put(SELECT_PDF_BUTTON_XPATH, DOWNLOAD_PDF_BUTTON_XPATH);

        map.forEach((documentTypeSelector, selectedDocumentSelector) -> {
            driver.navigate().to(siteUrl);

            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(documentTypeSelector));
            driver.findElement(documentTypeSelector).click();

            Actions act = new Actions(driver);
            act.doubleClick().perform();

            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.visibilityOfElementLocated(selectedDocumentSelector));
            driver.findElement(selectedDocumentSelector).click();

            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(DOWNLOADING_TITLE_XPATH));
            driver.findElement(DOWNLOADING_TITLE_XPATH).isDisplayed();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        });
        return driver.findElement(DOWNLOADING_TITLE_XPATH).getText();
    }
}


