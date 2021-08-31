import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TypoTest {

    WebDriver driver;
    final String correctText = "Sometimes you'll see a typo, other times you won't.";
    String randomLoadedText;

    @BeforeMethod
    void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(new ChromeOptions().addArguments("headless"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/typos");
        randomLoadedText = driver.findElement(By.xpath("//*[@id=\"content\"]/div/p[2]")).getText();
    }

    @AfterMethod
    void end() {
        driver.quit();
    }

    @Test
    void TextOfThePageIsCorrect() {
        Assert.assertEquals(randomLoadedText,correctText);
    }

}
