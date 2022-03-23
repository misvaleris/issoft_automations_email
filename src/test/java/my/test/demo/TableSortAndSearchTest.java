package my.test.demo;

import my.test.demo.table.TableInfo;
import my.test.demo.table.TableSortAndSearchDemo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.text.ParseException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class TableSortAndSearchTest {
    private TableSortAndSearchDemo tableSortAndSearchDemo;
    private WebDriver driver;

    @BeforeEach
    void setup() {
        driver = new ChromeDriver();
        tableSortAndSearchDemo = new TableSortAndSearchDemo(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private static Stream<Arguments> testValues() {
        List<TableInfo> expectedTableData = new ArrayList<>();
        expectedTableData.add(new TableInfo("A. Cox", "Junior Technical Author", "San Francisco"));
        expectedTableData.add(new TableInfo("G. Winters", "Accountant", "Tokyo"));
        expectedTableData.add(new TableInfo("M. Silva", "Marketing Designer", "London"));
        return Stream.of(
                Arguments.of(60, 300000, expectedTableData)
        );
    }

    @DisplayName(value = "Table sort and search test")
    @ParameterizedTest
    @MethodSource("testValues")
    void tableSortAndSearchTest(int age, int salary, List<TableInfo> expectedTableData) throws ParseException {
        List<TableInfo> actualTableData = tableSortAndSearchDemo.mapTableByFilter(age, salary);
        Assertions.assertEquals(expectedTableData,actualTableData, "d");
    }

    @AfterEach
    void cleanup() {
        driver.quit();
    }
}
