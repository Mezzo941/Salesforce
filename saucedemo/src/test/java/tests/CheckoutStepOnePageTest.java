package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.Items;

import static pages.Items.*;

public class CheckoutStepOnePageTest extends BaseTest {

    @DataProvider
    public Object[][] getData() {
        return new Object[][]{
                {"standard_user", "secret_sauce", "", "Wall", "111000","Error: First Name is required"},
                {"standard_user", "secret_sauce", "John", "", "111000","Error: Last Name is required"},
                {"standard_user", "secret_sauce", "John", "Wall", "","Error: Postal Code is required"},
        };
    }

    @Test(description = "try to continue checkout with valid data and full bucket")
    public void continueCheckoutWithValidDataIsSuccessful() {
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
    }

    @Test(description = "try to continue checkout with empty name, then empty password, then empty postal code", dataProvider = "getData")
    public void continueCheckoutWithEmptyFieldsIsImpossible(String user,String password, String firstName, String lastName, String postalCode, String error) {
        Items[] threeRandomItemsArray = {SAUCE_LABS_BACKPACK, SAUCE_LABS_BIKE_LIGHT, SAUCE_LABS_BOLT_TSHIRT};
        loginPage.open();
        loginPage.authorization(user, password);
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
        checkoutStepOnePage.enterCheckoutInfoAndContinue(firstName, lastName, postalCode);
        Assert.assertFalse(checkoutStepTwoPage.isOpened());
        Assert.assertEquals(checkoutStepOnePage.getErrorMsg(), error);
    }

}
