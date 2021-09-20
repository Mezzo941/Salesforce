package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BMmenuTest extends BaseTest {

    public void loginToThePersonalAccount() {

        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

    }

    @Test
    public void allItemsHaveOpened() {
        loginToThePersonalAccount();
        driver.findElement(By.id("item_4_title_link")).click();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("inventory_sidebar_link")).click();
        Assert.assertEquals(driver.findElement(By.cssSelector("[class=title]")).getText(), "PRODUCTS");
    }

    @Test
    public void aboutHaveOpened() {
        loginToThePersonalAccount();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("about_sidebar_link")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://saucelabs.com/");
    }

    @Test
    public void LogoutIsSuccess() {
        loginToThePersonalAccount();
        driver.findElement(By.id("react-burger-menu-btn")).click();
        driver.findElement(By.id("logout_sidebar_link")).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
    }

    @Test
    public void resetAppStateIsWorking() {
        //нет требований, а так есть баг
    }
}


