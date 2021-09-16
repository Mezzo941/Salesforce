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
        Assert.assertEquals(cartPage.getItemPriceFromTheCart(SAUCE_LABS_BACKPACK.getName()),SAUCE_LABS_BACKPACK.getPrice());
        cartPage.removeItemFromTheCartButton(SAUCE_LABS_BACKPACK.getName());
        isItemRemovedFastAssert(SAUCE_LABS_BACKPACK.getName());
    }

    /*@Test
    public void checkoutWithEmptyCartIsImpossible() {
        loginToThePersonalAccount();
        driver.findElement(By.id("shopping_cart_container")).click();
        driver.findElement(By.id("checkout")).click();
        Assert.assertEquals(driver.findElements(By.xpath("//*[text()='You can't make an order with empty cart']")).size(), 1);
    }*/

}
