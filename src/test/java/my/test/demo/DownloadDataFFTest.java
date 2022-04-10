package my.test.demo;

import my.test.demo.table.DownloadData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.File;

public class DownloadDataFFTest {
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

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.dir", downloadFilepath);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/csv, text/csv, text/plain,application/octet-stream doc xls pdf txt");
        FirefoxOptions option = new FirefoxOptions();
        option.setProfile(profile);

        driver = new FirefoxDriver(option);
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
