import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeValidationTests extends BaseTest {

    @Test
    public void LoginWithEmptyPasswordIsImpossible() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test=error]")).getText(), "Epic sadface: Password is required");
    }


    @Test
    public void LoginWithEmptyFieldsIsImpossible() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test=error]")).getText(), "Epic sadface: Username is required");
    }

    @Test
    public void LoginWithNotValidDateIsImpossible(){
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("standard_user");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test=error]")).getText(), "Epic sadface: Username and password do not match any user in this service");
    }

}
