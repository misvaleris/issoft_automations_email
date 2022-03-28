package my.test.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class MultiSelectListDemo {
    private final WebDriver driver;

    public MultiSelectListDemo(WebDriver driver) {
        this.driver = driver;
    }

    public boolean multiDropDownSelect() {

        driver.navigate().to("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
        WebElement dropDownList = driver.findElement(By.xpath("//select[@id='multi-select']"));
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(dropDownList));
        List<WebElement> dropDownOptions = driver.findElements(By.xpath("//select[@id='multi-select']/option"));

        boolean result;
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int randomIndex = random.nextInt(dropDownOptions.size());
            Select drpValue = new Select(dropDownList);
            WebElement selectFirstValueButton = driver.findElement(By.xpath("//button[@id='printMe']"));

            drpValue.selectByIndex(randomIndex);
            selectFirstValueButton.click();

            WebElement selectedOption = drpValue.getFirstSelectedOption();
            String selectedValueExpected = selectedOption.getText();

            WebElement dropDownOptionSelected = driver.findElement(By.xpath("//p[contains(text(), 'selected') and @class = 'getall-selected']"));
            String selectedValueActual = dropDownOptionSelected.getText();

            result = selectedValueActual.contains(selectedValueExpected);
            if (!result) {
                return false;
            }
            drpValue.deselectByIndex(randomIndex);
        }
        return true;
    }

}
