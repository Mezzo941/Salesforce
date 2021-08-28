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

public class AddRemoveElementsTest {

    WebDriver driver;
    WebElement buttonAdd, buttonRemove;
    List<WebElement> buttonStack = new ArrayList<>();

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver(new ChromeOptions().addArguments("start-maximized","headless"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        buttonAdd = driver.findElement(By.xpath("//*[@id=\"content\"]/div/button"));
    }

    @AfterMethod
    public void end() {
        driver.quit();
    }

    @Test
    public void Add2elementsRemove1CheckRemains1() {
        buttonAdd.click();
        buttonAdd.click();
        buttonRemove = driver.findElement(By.xpath("//*[@id=\"elements\"]/button[2]"));
        buttonRemove.click();
        buttonStack = driver.findElements(By.cssSelector("[class=added-manually]"));
        assertEquals(buttonStack.size(),1);
    }

}
