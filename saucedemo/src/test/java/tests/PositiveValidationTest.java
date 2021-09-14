package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveValidationTest extends BaseTest{

    @Test
    public void lockedUserCantlogin(){
        loginPage.open();
        loginPage.authorization("standard_user","secret_sauce");
        Assert.assertEquals(driver.findElement(By.cssSelector("[data-test=error]")).getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

}
