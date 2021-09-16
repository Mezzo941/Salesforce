package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import static pages.Item.*;


public class CartPageTest extends BaseTest {

    @Test
    public void itemsSuccessfulRemovedFromCart() {
        loginPage.open();
        loginPage.authorization("standard_user","secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
        catalogPage.addOrRemoveItemFromCart(SAUCE_LABS_BACKPACK.getName());
        catalogPage.openCart();
        Assert.assertTrue(cartPage.isOpened());
        Assert.assertTrue(cartPage.isItemIntoTheCart(SAUCE_LABS_BACKPACK.getName()));
        cartPage.RemoveItemFromTheCartButton(SAUCE_LABS_BACKPACK.getName());
        isItemRemovedFastAssert(SAUCE_LABS_BACKPACK.getName());
    }

}
