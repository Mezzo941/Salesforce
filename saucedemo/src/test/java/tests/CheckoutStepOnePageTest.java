package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Items;

import static pages.Items.*;

public class CheckoutStepOnePageTest extends BaseTest{

    @Test
    public void continueCheckoutWithValidDataIsSuccessful(){
        Items[] threeRandomItemsArray = {SAUCE_LABS_BACKPACK, SAUCE_LABS_BIKE_LIGHT,SAUCE_LABS_BOLT_TSHIRT};
        loginPage.open();
        loginPage.authorization("standard_user","secret_sauce");
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
        cartPage.checkout();
        checkoutStepOnePage.enterCheckoutInfo("John","Wall","111000");
        Assert.assertTrue(checkoutStepTwoPage.isOpened("CHECKOUT: OVERVIEW"));
        for (Items items : threeRandomItemsArray) {
            Assert.assertTrue(checkoutStepTwoPage.isItemIntoThePage(items.getName()));
            Assert.assertEquals(checkoutStepTwoPage.getItemPriceFromThePage(items.getName()),items.getPrice());
        }
    }

}
