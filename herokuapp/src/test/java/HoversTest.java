import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HoversTest {

    WebDriver driver;
    Actions action;
    List<String> links;
    List<String> pageNames;
    final String[] trueUserNames = {"Леха", "Серега", "Наташка"}; //рандом имена типо реальных юзеров
    //String[] falseUserNames = {"name: user1", "name: user2", "name: user3"};
    //для тестирование работоспособности теста UserNameCheckIsSuccessfully

    @BeforeMethod
    void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        links = new ArrayList<>();
        pageNames = new ArrayList<>();
        driver = new ChromeDriver(new ChromeOptions().addArguments("headless"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        action = new Actions(driver);
        driver.get("http://the-internet.herokuapp.com/hovers");
        for (int i = 1; i < 4; i++) {
            links.add(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[" + i + "]/div/a ")).getAttribute("href"));
        }
    }

    @AfterMethod
    void end() {
        driver.quit();
    }

    @Test
    void UserNameCheckIsSuccessfully() {
        for (int i = 0; i < trueUserNames.length; i++) {
            action.moveToElement(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[" + (i + 1) + "]/img"))).perform();
            pageNames.add(driver.findElement(By.xpath("//*[@id=\"content\"]/div/div[" + (i + 1) + "]/div/h5")).getText());
            Assert.assertEquals(trueUserNames[i], pageNames.get(i));
        }
    }

    @Test
    void NotFound404DoNotExists() {
        for (String link : links) {
            driver.get(link);
            Assert.assertNotEquals(driver.findElement(By.tagName("h1")).getText(), "Not Found");
        }
    }

}
