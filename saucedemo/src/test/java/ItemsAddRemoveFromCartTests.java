import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemsAddRemoveFromCartTests extends BaseTest {

    String[] items = {"Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)"};
    String[] itemsAddRemoveButtonsPaths = new String[6];
    String[] removeFromTheCartButtonPaths = new String[6];

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

        double price = 0;

        for (int i = 0; i < items.length; i++) {
            driver.findElement(By.xpath(itemsAddRemoveButtonsPaths[i])).click();
            driver.findElement(By.id("shopping_cart_container")).click();
            Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Your Cart']")).isDisplayed());
            removeFromTheCartButtonPaths[i] = "//*[contains(text(),'" + items[i] + "')]/ancestor::div[@class='cart_list']//button";
            Assert.assertEquals(driver.findElement(By.xpath("//*[text()='" + items[i] + "']")).getText(), items[i]);
            price += Double.parseDouble(driver.findElement(By.xpath("//*[text()='" + items[i] + "']/ancestor::*[@class='cart_item']//*[@class='inventory_item_price']")).getText().substring(1));
            if (i <= items.length - 2) { //возвращаемся к покупкам только в случае, если в корзине менее 6 товаров, в противном случае остаемся в корзине, когда все товары там
                driver.findElement(By.id("continue-shopping")).click();
            }
        }
        Assert.assertEquals(driver.findElement(By.xpath("//*[@class='shopping_cart_badge']")).getText(), "6");
        Assert.assertEquals(driver.findElements(By.cssSelector("[class=cart_quantity]")).size(), 6);
        Assert.assertEquals(price, 129, 94);

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

        for (int i = 0; i < items.length; i++) {
            driver.findElement(By.xpath(removeFromTheCartButtonPaths[i])).click();
        }

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
        Assert.assertEquals(driver.findElement(By.xpath(itemsAddRemoveButtonsPaths[0])).getText(),"ADD TO CART");
    }

}
