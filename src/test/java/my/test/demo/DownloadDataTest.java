package my.test.demo;

import my.test.demo.table.DownloadData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DownloadDataTest {
    private DownloadData downloadData;
    private WebDriver driver;

    @BeforeEach
    void setup() {
        String parentDirectoryPath = System.getProperty("user.dir");
        String downloadFilepath = parentDirectoryPath + "/target/";
        File downloadFolder = new File(downloadFilepath);
        if (!downloadFolder.exists()) {
            downloadFolder.mkdir();
        }
        Map<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.default_directory", downloadFilepath);
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);
        downloadData = new DownloadData(driver);
    }

    @DisplayName(value = "Loading the data Dynamically test")
    @Test
    void loadUserInfoTest() {
        downloadData.downloadData();
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }
}
