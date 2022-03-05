package my.test.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmailVariables {
    WebDriver driver = new ChromeDriver();
    WebElement element1 = driver.findElement(By.className("mail-ComposeButton-Text"));
    WebElement element2 = driver.findElement(By.cssSelector(".mail-ComposeButton-Text"));
    WebElement element3 = driver.findElement(By.xpath("//span[@class='mail-ComposeButton-Text']"));
    WebElement element4 = driver.findElement(By.tagName("div"));
    WebElement element5 = driver.findElement(By.id("nb-checkbox_0"));
    WebElement element6 = driver.findElement(By.name("referrer"));
    WebElement element7 = driver.findElement(By.linkText("click here"));
    WebElement element8 = driver.findElement(By.partialLinkText("here"));
}

