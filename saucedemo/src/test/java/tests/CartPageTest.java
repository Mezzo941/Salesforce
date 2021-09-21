package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Items;
import static pages.Items.*;

public class CartPageTest extends BaseTest {

    @Test
    public void oneItemRemovedFromTheCart() {
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        catalogPage.addOrRemoveItemFromCart(SAUCE_LABS_BACKPACK.getName());
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened());
        Assert.assertTrue(cartPage.isItemOnThePage(SAUCE_LABS_BACKPACK.getName()));
        Assert.assertEquals(cartPage.getItemPriceFromThePage(SAUCE_LABS_BACKPACK.getName()), SAUCE_LABS_BACKPACK.getPrice());
        cartPage.removeItemFromTheCart(SAUCE_LABS_BACKPACK.getName());
        Assert.assertTrue(cartPage.isCartEmpty());
    }

    @Test
    public void allItemsRemovedFromTheCart() {
        Items[] itemsArray = Items.values();
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        for (Items items : itemsArray) {
            catalogPage.addOrRemoveItemFromCart(items.getName());
        }
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened());
        for (Items items : itemsArray) {
            Assert.assertTrue(cartPage.isItemOnThePage(items.getName()));
            Assert.assertEquals(cartPage.getItemPriceFromThePage(items.getName()), items.getPrice());
        }
        for (Items items : itemsArray) {
            cartPage.removeItemFromTheCart(items.getName());
        }
        Assert.assertTrue(cartPage.isCartEmpty());
    }

    @Test
    public void checkoutWithEmptyCartIsImpossible() {
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened());
        Assert.assertTrue(cartPage.isCartEmpty());
        cartPage.checkout();
        Assert.assertFalse(checkoutStepOnePage.isOpened());
    }

    @Test
    public void check() {
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        catalogPage.openCart();
        cartPage.isItemOnThePage(SAUCE_LABS_BACKPACK.getName());
    }

}
