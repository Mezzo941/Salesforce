import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class NotificationMessageTest {

    WebDriver driver;
    WebElement msgButton;
    String msg;
    String actualMsg = "Action successful";

    @BeforeMethod
    void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(new ChromeOptions().addArguments("headless"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/notification_message_rendered");
        msgButton = driver.findElement(By.linkText("Click here"));
        msgButton.click();
        msg = driver.findElement(By.id("flash")).getText();
    }

    @AfterMethod
    void end() {
        driver.quit();
    }

    @Test
    void notificationMessageIsDisplay() {
        Assert.assertEquals(msg.substring(0, msg.length() - 2), actualMsg);
    }

}


