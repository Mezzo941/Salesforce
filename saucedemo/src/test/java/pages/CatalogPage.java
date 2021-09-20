package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage {

    private static final By CART_BUTTON = By.className("shopping_cart_link");

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public By getItemsAddButton(String itemName) {
        return By.xpath("//*[text()='" + itemName + "']/ancestor::*[@class = 'inventory_item']//button");
    }

    public void addOrRemoveItemFromCart(String itemName) {
        driver.findElement(getItemsAddButton(itemName)).click();
    }

    public void openCart() {
        driver.findElement(CART_BUTTON).click();
    }

}
