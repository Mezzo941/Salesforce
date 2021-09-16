package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Item;

import static pages.Item.*;

public class CatalogPageTest extends BaseTest {

    @Test
    public void itemsSuccessfulAddedToCart() {
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        catalogPage.addOrRemoveItemFromCart(SAUCE_LABS_BACKPACK.getName());
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened());
        Assert.assertTrue(cartPage.isItemIntoTheCart(SAUCE_LABS_BACKPACK.getName()));
    }

    @Test
    public void twoTimesUseAddItemButtonWillRemoveItemFromTheCart() {
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        catalogPage.addOrRemoveItemFromCart(SAUCE_LABS_BACKPACK.getName());
        //Thread.sleep(5000);
        catalogPage.addOrRemoveItemFromCart(SAUCE_LABS_BACKPACK.getName());
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened());
        isItemRemovedFastAssert(SAUCE_LABS_BACKPACK.getName());
    }

    @Test
    public void allItemsFromTheCatalogAddedToTheCart() {
        Item[] itemsArray = Item.values();
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        for (Item item : itemsArray) {
            catalogPage.addOrRemoveItemFromCart(item.getName());
        }
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened());
        for (Item item : itemsArray) {
            Assert.assertTrue(cartPage.isItemIntoTheCart(item.getName()));
        }

    }

    @Test
    public void buttonAddSwitchToRemoveAfterClick() {
        /*loginToThePersonalAccount();
        driver.findElement(By.xpath(itemsAddRemoveButtonsPaths[0])).click();
        Assert.assertEquals(driver.findElement(By.xpath(itemsAddRemoveButtonsPaths[0])).getText(), "REMOVE");
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
        driver.findElement(By.id("continue-shopping")).click();
        Assert.assertEquals(driver.findElement(By.xpath(itemsAddRemoveButtonsPaths[0])).getText(), "ADD TO CART");*/
    }

    @Test
    public void checkoutWithEmptyCartIsImpossible() {
        /*loginToThePersonalAccount();
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("checkout")).click();
        Assert.assertEquals(driver.findElements(By.xpath("//*[text()='You can't make an order with empty cart']")).size(), 1);*/
    }

}
