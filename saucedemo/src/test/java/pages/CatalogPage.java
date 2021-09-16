package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage {

    static final By CART_BUTON = By.className("shopping_cart_link");

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public By getItemsPath(String itemName) {
        return By.xpath("//*[text()='" + itemName + "']/ancestor::*[@class = 'inventory_item']//button");
    }

    public void addOrRemoveItemFromCart(String itemName) {
        driver.findElement(getItemsPath(itemName)).click();
    }

    public void openCart() {
        driver.findElement(CART_BUTON).click();
    }


}
//*[contains(text(),'Sauce Labs Bike Light')]/ancestor::div[@class='cart_item_label']//button