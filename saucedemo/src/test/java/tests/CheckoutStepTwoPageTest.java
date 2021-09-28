package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Items;

import static pages.Items.*;

public class CheckoutStepTwoPageTest extends BaseTest {

    @Test(description = "check correct total price summed by all items prices before final checkout")
    public void isTotalCorrect() {
        Items[] threeRandomItemsArray = {SAUCE_LABS_BACKPACK, SAUCE_LABS_BIKE_LIGHT, SAUCE_LABS_BOLT_TSHIRT};
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            catalogPage.addOrRemoveItemFromCart(items.getName());
        }
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            Assert.assertTrue(cartPage.isItemOnThePage(items.getName()));
            Assert.assertEquals(cartPage.getItemPriceFromThePage(items.getName()), items.getPrice());
        }
        cartPage.checkout();
        checkoutStepOnePage.enterCheckoutInfoAndContinue("John", "Wall", "111000");
        Assert.assertTrue(checkoutStepTwoPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            Assert.assertTrue(checkoutStepTwoPage.isItemOnThePage(items.getName()));
            Assert.assertEquals(checkoutStepTwoPage.getItemPriceFromThePage(items.getName()), items.getPrice());
        }
        Double total = checkoutStepTwoPage.getItemTotal() + checkoutStepTwoPage.getTax();
        Assert.assertEquals(total, checkoutStepTwoPage.getTotal());
    }

    @Test(description = "check correct tax that calculated from summed by all items prices before final checkout")
    public void isTaxCorrect() {
        Items[] threeRandomItemsArray = {SAUCE_LABS_BACKPACK, SAUCE_LABS_BIKE_LIGHT, SAUCE_LABS_BOLT_TSHIRT};
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            catalogPage.addOrRemoveItemFromCart(items.getName());
        }
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            Assert.assertTrue(cartPage.isItemOnThePage(items.getName()));
            Assert.assertEquals(cartPage.getItemPriceFromThePage(items.getName()), items.getPrice());
        }
        cartPage.checkout();
        checkoutStepOnePage.enterCheckoutInfoAndContinue("John", "Wall", "111000");
        Assert.assertTrue(checkoutStepTwoPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            Assert.assertTrue(checkoutStepTwoPage.isItemOnThePage(items.getName()));
            Assert.assertEquals(checkoutStepTwoPage.getItemPriceFromThePage(items.getName()), items.getPrice());
        }
        Double tax = checkoutStepTwoPage.getItemTotal() * 0.08;
        Assert.assertEquals(tax, checkoutStepTwoPage.getTax());
    }

    @Test(description = "check correct total order price that calculated from summed tax and total items prices before final checkout")
    public void isTotalItemCorrect() {
        Items[] threeRandomItemsArray = {SAUCE_LABS_BACKPACK, SAUCE_LABS_BIKE_LIGHT, SAUCE_LABS_BOLT_TSHIRT};
        Double totalPrice = 0.0;
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            catalogPage.addOrRemoveItemFromCart(items.getName());
        }
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            Assert.assertTrue(cartPage.isItemOnThePage(items.getName()));
            Assert.assertEquals(cartPage.getItemPriceFromThePage(items.getName()), items.getPrice());
        }
        cartPage.checkout();
        checkoutStepOnePage.enterCheckoutInfoAndContinue("John", "Wall", "111000");
        Assert.assertTrue(checkoutStepTwoPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            totalPrice += checkoutStepTwoPage.getItemPriceFromThePage(items.getName());
            Assert.assertTrue(checkoutStepTwoPage.isItemOnThePage(items.getName()));
            Assert.assertEquals(checkoutStepTwoPage.getItemPriceFromThePage(items.getName()), items.getPrice());
        }
        Assert.assertEquals(checkoutStepTwoPage.getItemTotal(), totalPrice);
    }

    @Test(groups = "smoke", description = "check success finishing the order")
    public void afterUseFinishButtonWillCompleteOrder() {
        Items[] threeRandomItemsArray = {SAUCE_LABS_BACKPACK, SAUCE_LABS_BIKE_LIGHT, SAUCE_LABS_BOLT_TSHIRT};
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            catalogPage.addOrRemoveItemFromCart(items.getName());
        }
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            Assert.assertTrue(cartPage.isItemOnThePage(items.getName()));
            Assert.assertEquals(cartPage.getItemPriceFromThePage(items.getName()), items.getPrice());
        }
        cartPage.checkout();
        checkoutStepOnePage.enterCheckoutInfoAndContinue("John", "Wall", "111000");
        Assert.assertTrue(checkoutStepTwoPage.isOpened());
        for (Items items : threeRandomItemsArray) {
            Assert.assertTrue(checkoutStepTwoPage.isItemOnThePage(items.getName()));
            Assert.assertEquals(checkoutStepTwoPage.getItemPriceFromThePage(items.getName()), items.getPrice());
        }
        checkoutStepTwoPage.completeOrder();
        Assert.assertTrue(completePage.isOpened());
        Assert.assertTrue(completePage.isCompleteMsgShow());
    }

}
