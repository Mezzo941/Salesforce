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
    List<WebElement> checkboxes = new ArrayList<>();

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(new ChromeOptions().addArguments("start-maximized"));
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
        boolean isSelected = checkboxes.get(0).isSelected();
        assertFalse(isSelected);
    }

    @Test
    public void Checkbox1Checked() {
        checkboxes.get(0).click();
        boolean isSelected = checkboxes.get(0).isSelected();
        assertTrue(isSelected);
    }

    @Test
    public void Checkbox2Checked() {
        boolean isSelected = checkboxes.get(1).isSelected();
        assertTrue(isSelected);
    }

    @Test
    public void Checkbox2Unchecked() {
        checkboxes.get(1).click();
        boolean isSelected = checkboxes.get(1).isSelected();
        assertFalse(isSelected);
    }

}
