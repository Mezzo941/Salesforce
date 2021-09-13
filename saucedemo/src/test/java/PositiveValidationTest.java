import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveValidationTest extends BaseTest{

    @Test
    public void lockedUserCantlogin(){
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("locked_out_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test=error]")).getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

}
