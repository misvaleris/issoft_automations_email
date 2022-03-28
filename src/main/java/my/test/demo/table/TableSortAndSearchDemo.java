package my.test.demo.table;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class TableSortAndSearchDemo {

    private static final String TABLE_COLUMN_XPATH_TEMPLATE = "//table[@id='example']//tr[%s]/td[%s]";
    private static final NumberFormat SALARY_FORMAT = NumberFormat.getCurrencyInstance(Locale.US);
    private final WebDriver driver;

    public TableSortAndSearchDemo(WebDriver driver) {
        this.driver = driver;
    }

    public List<TableInfo> mapTableByFilter(int age, int salary){
        driver.navigate().to("https://demo.seleniumeasy.com/table-sort-search-demo.html");
        WebElement showEntriesDropDown = driver.findElement(By.xpath("//select[@name='example_length']"));

        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(showEntriesDropDown));
        Select drpValue = new Select(showEntriesDropDown);
        drpValue.selectByValue("10");

        List<TableInfo> result = new ArrayList<>();
        int numOfRow = driver.findElements(By.xpath("//table[@id='example']//tbody/tr")).size();
        if (numOfRow != 0) {
            int rowPosition = 1;
            while (rowPosition <= numOfRow) {
                try {
                    addTableInfoIfMatchFilter(result, rowPosition, age, salary);
                } catch (ParseException e) {
                    System.out.printf("Error on parse row salary %s", salary);
                    e.printStackTrace();
                }

                if (rowPosition == numOfRow) {
                    WebElement nextButton = driver.findElement(By.xpath("//a[@id='example_next']"));
                    if (isButtonActive(nextButton)) {
                        nextButton.click();
                        rowPosition = 1;
                        numOfRow = driver.findElements(By.xpath("//table[@id='example']//tbody/tr")).size();
                    }
                }
                rowPosition++;
            }
        }
        return result;
    }

    private boolean isButtonActive(WebElement nextButton) {
        return !nextButton.getAttribute("class").contains("disabled");
    }

    private void addTableInfoIfMatchFilter(List<TableInfo> result, int rowPosition, int age, int salary) throws ParseException {
        int columnAge = getAge(rowPosition);
        if (columnAge > age) {
            int columnSalary = getSalary(rowPosition);
            if (columnSalary <= salary) {
                result.add(buildTableInfo(rowPosition));
            }
        }
    }

    private int getAge(int rowPosition) {
        String columnAgeXPath = String.format(TABLE_COLUMN_XPATH_TEMPLATE, rowPosition, ColumnTypes.AGE.columnPosition);
        String columnAge = driver.findElement(By.xpath(columnAgeXPath)).getText();
        return Integer.parseInt(columnAge);
    }

    private int getSalary(int rowPosition) throws ParseException {
        String columnSalaryXPath = String.format(TABLE_COLUMN_XPATH_TEMPLATE, rowPosition, ColumnTypes.SALARY.columnPosition);
        String salaryString = driver.findElement(By.xpath(columnSalaryXPath)).getText();
        Number value = SALARY_FORMAT.parse(salaryString);
        return value.intValue();
    }

    private TableInfo buildTableInfo(int linePosition) {
        String columnNameXPath = String.format(TABLE_COLUMN_XPATH_TEMPLATE, linePosition, ColumnTypes.NAME.columnPosition);
        String name = driver.findElement(By.xpath(columnNameXPath)).getText();

        String columnPositionXPath = String.format(TABLE_COLUMN_XPATH_TEMPLATE, linePosition, ColumnTypes.POSITION.columnPosition);
        String position = driver.findElement(By.xpath(columnPositionXPath)).getText();

        String columnOfficeXPath = String.format(TABLE_COLUMN_XPATH_TEMPLATE, linePosition, ColumnTypes.OFFICE.columnPosition);
        String office = driver.findElement(By.xpath(columnOfficeXPath)).getText();

        return new TableInfo(name, position, office);
    }

    private enum ColumnTypes {
        NAME(1),
        POSITION(2),
        OFFICE(3),
        AGE(4),
        SALARY(6);

        ColumnTypes(int columnPosition) {
            this.columnPosition = columnPosition;
        }

        private final int columnPosition;
    }
}