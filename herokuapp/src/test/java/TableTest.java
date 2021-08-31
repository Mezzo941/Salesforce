import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TableTest {

    WebDriver driver;
    List<String> cellsValues;
    final String[] emails = {"jsmith@gmail.com", "fbach@yahoo.com", "jdoe@hotmail.com", "tconway@earthlink.net"};

    @BeforeMethod
    void setup() {
        cellsValues = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(new ChromeOptions().addArguments("headless"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/tables#delete");
        for (int i = 1; i < emails.length + 1; i++) {
            cellsValues.add(driver.findElement(By.xpath("//*[@id=\"table1\"]/tbody/tr[" + i + "]/td[3]")).getText());
        }
    }

    @AfterMethod
    void end() {
        driver.quit();
    }

    @Test
    void cellsAreFull() {
        for (int i = 0; i < emails.length; i++) {
            Assert.assertEquals(cellsValues.get(i), emails[i]);
        }
    }

}

