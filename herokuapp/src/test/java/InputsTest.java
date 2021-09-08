import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class InputsTest {

    WebDriver driver;
    WebElement inputs;

    @BeforeMethod
    public void setup() {
        inputs = driver.findElement(By.cssSelector("[type=number]"));
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(new ChromeOptions().addArguments("headless"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/inputs");
    }

    @AfterMethod
    public void end() {
        driver.quit();
    }

    @Test
    void numberInputArrowUpIsWork() {
        for (int i = 1; i < 21; i++) {
            inputs.sendKeys(Keys.ARROW_UP);
        }
        String content = inputs.getAttribute("value");
        Assert.assertEquals(content, "20");
    }

    @Test
    void numberInputArrowDownIsWork() {
        for (int i = 1; i < 21; i++) {
            inputs.sendKeys(Keys.ARROW_DOWN);
        }
        String content = inputs.getAttribute("value");
        Assert.assertEquals(content, "-20");
    }

    @Test
    void letterInputShouldnotWorking() {
        inputs.sendKeys("мама люба давай");
        String content = inputs.getAttribute("value");
        Assert.assertEquals(content, "");
    }

    @Test
    void charsInputShouldnotWorking() {
        inputs.sendKeys("+-/%#");
        String content = inputs.getAttribute("value");
        Assert.assertEquals(content, "");
    }

}
