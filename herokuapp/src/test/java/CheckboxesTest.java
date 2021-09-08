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

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class CheckboxesTest {

    WebDriver driver;
    List<WebElement> checkboxes;

    @BeforeMethod
    public void setup() {
        checkboxes = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(new ChromeOptions().addArguments("headless"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/checkboxes");
        checkboxes = driver.findElements(By.cssSelector("[type=checkbox]"));
    }

    @AfterMethod
    public void end() {
        driver.quit();
    }

    @Test
    public void Checkbox1Unchecked() {
        assertFalse(checkboxes.get(0).isSelected());
    }

    @Test
    public void Checkbox1Checked() {
        checkboxes.get(0).click();
        assertTrue(checkboxes.get(0).isSelected());

    }

    @Test
    public void Checkbox2Checked() {
        assertTrue(checkboxes.get(1).isSelected());
    }

    @Test
    public void Checkbox2Unchecked() {
        checkboxes.get(1).click();
        assertFalse(checkboxes.get(1).isSelected());
    }

}
