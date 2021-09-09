import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class AddToCartTest extends BaseTest {

    List<WebElement> itemStackButtons;
    String[] itemStack = {"Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"};

    public void itemsGoToCart() {
        itemStackButtons = new ArrayList<>();
        for (int i = 0; i < itemStack.length; i++) {
            itemStackButtons.add(driver.findElement(By.xpath("//*[text()='" + itemStack[i] + "']/ancestor::*[@class = 'inventory_item']//button")));
            itemStackButtons.get(i).click();
            driver.findElement(By.id("shopping_cart_container")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Your Cart']")).isDisplayed());
            driver.findElement(By.id("continue-shopping")).click();
        }
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='shopping_cart_badge']")).getText(),"6");
    }

    @Test
    public void itemsSuccessAddedToCart() {
        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();
        itemsGoToCart();
    }

}
