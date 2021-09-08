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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DropdownTest {

    WebDriver driver;
    List<WebElement> dropDown;

    @BeforeMethod
    public void setup() {
        dropDown = new ArrayList<>();
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(new ChromeOptions().addArguments("headless"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/dropdown");
        dropDown = driver.findElements(By.xpath("//*[@id=\"dropdown\"]/option"));
    }

    @AfterMethod
    public void end() {
        driver.quit();
    }

    @Test
    public void dropDownItemsAreExist() {
        assertEquals(dropDown.size(),3);
    }

    @Test
    public void option1Selected(){
        dropDown.get(1).click();
        assertTrue(dropDown.get(1).isSelected());
    }

    @Test
    public void option2Selected(){
        dropDown.get(2).click();
        assertTrue(dropDown.get(2).isSelected());
    }

}
