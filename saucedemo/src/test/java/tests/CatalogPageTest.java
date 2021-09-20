package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Items;

import static pages.Items.*;

public class CatalogPageTest extends BaseTest {

    @Test
    public void oneItemAddedToTheCart() {
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened("PRODUCTS"));
        catalogPage.addOrRemoveItemFromCart(SAUCE_LABS_BACKPACK.getName());
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened("YOUR CART"));
        Assert.assertTrue(cartPage.isItemIntoThePage(SAUCE_LABS_BACKPACK.getName()));
        Assert.assertEquals(cartPage.getItemPriceFromThePage(SAUCE_LABS_BACKPACK.getName()), SAUCE_LABS_BACKPACK.getPrice());
    }


    @Test
    public void allItemsAddedToTheCart() {
        Items[] itemsArray = Items.values();
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened("PRODUCTS"));
        for (Items items : itemsArray) {
            catalogPage.addOrRemoveItemFromCart(items.getName());
        }
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened("YOUR CART"));
        for (Items items : itemsArray) {
            Assert.assertTrue(cartPage.isItemIntoThePage(items.getName()));
            Assert.assertEquals(cartPage.getItemPriceFromThePage(items.getName()), items.getPrice());
        }
    }

    @Test
    public void threeRandomItemsAddedToTheCart() {
        Items[] threeRandomItemsArray = {SAUCE_LABS_BACKPACK, SAUCE_LABS_BIKE_LIGHT,SAUCE_LABS_BOLT_TSHIRT};
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened("PRODUCTS"));
        for (Items items : threeRandomItemsArray) {
            catalogPage.addOrRemoveItemFromCart(items.getName());
        }
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened("YOUR CART"));
        for (Items items : threeRandomItemsArray) {
            Assert.assertTrue(cartPage.isItemIntoThePage(items.getName()));
            Assert.assertEquals(cartPage.getItemPriceFromThePage(items.getName()), items.getPrice());
        }
    }

    @Test
    public void twoTimesUseAddItemButtonWillRemoveItemFromTheCart() {
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened("PRODUCTS"));
        catalogPage.addOrRemoveItemFromCart(SAUCE_LABS_BACKPACK.getName());
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened("YOUR CART"));
        Assert.assertTrue(cartPage.isItemIntoThePage(SAUCE_LABS_BACKPACK.getName()));
        cartPage.continueShopping();
        catalogPage.addOrRemoveItemFromCart(SAUCE_LABS_BACKPACK.getName());
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened("YOUR CART"));
        fastAssert(true,cartPage.isItemRemoved(SAUCE_LABS_BACKPACK.getName()));
    }

}










