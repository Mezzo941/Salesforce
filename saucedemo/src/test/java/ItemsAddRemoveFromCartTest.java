import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemsAddRemoveFromCartTest extends BaseTest {

    String[] items = {"Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"};
    String[] itemsAddRemoveButtonsPaths = new String[6];

    public void loginToThePersonalAccount() {

        driver.get("https://www.saucedemo.com");
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        for (int i = 0; i < items.length; i++) {
            itemsAddRemoveButtonsPaths[i] = "//*[text()='" + items[i] + "']/ancestor::*[@class = 'inventory_item']//button";
        }

    }

    public void itemsGoToCart() {

        driver.findElement(By.xpath(itemsAddRemoveButtonsPaths[0])).click();
        driver.findElement(By.id("shopping_cart_container")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Your Cart']")).isDisplayed());
        Assert.assertEquals(driver.findElement(By.xpath("//*[text()='" + items[0] + "']")).getText(), items[0]);
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='shopping_cart_badge']")).getText(), "1");
        Assert.assertEquals(driver.findElements(By.cssSelector("[class=cart_quantity]")).size(), 1);

    }

    @Test
    public void itemsSuccessfulAddedToCart() {
        loginToThePersonalAccount();
        itemsGoToCart();
    }

    @Test
    public void itemsSuccessfulRemovedFromCart() {

        loginToThePersonalAccount();
        itemsGoToCart();
        driver.findElement(By.xpath("//*[contains(text(),'" + items[0] + "')]/ancestor::div[@class='cart_list']//button")).click();
        Assert.assertEquals(driver.findElements(By.cssSelector("[class=cart_quantity]")).size(), 0);

    }

    @Test
    public void buttonAddSwitchToRemoveAfterClick() {
        loginToThePersonalAccount();
        driver.findElement(By.xpath(itemsAddRemoveButtonsPaths[0])).click();
        Assert.assertEquals(driver.findElement(By.xpath(itemsAddRemoveButtonsPaths[0])).getText(), "REMOVE");
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        driver.findElement(By.id("continue-shopping")).click();
        Assert.assertEquals(driver.findElement(By.xpath(itemsAddRemoveButtonsPaths[0])).getText(), "ADD TO CART");
    }

    @Test
    public void checkoutWithEmptyCartIsImpossible() {
        loginToThePersonalAccount();
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("checkout")).click();
        Assert.assertEquals(driver.findElements(By.xpath("//*[text()='You can't make an order with empty cart']")).size(), 1);
    }

}
