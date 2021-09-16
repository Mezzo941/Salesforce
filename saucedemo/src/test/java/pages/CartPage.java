package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class CartPage extends BasePage {

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void removeItemFromTheCartButton(String item) {
        driver.findElement(By.xpath("//*[contains(text(),'" + item + "')]/" +
                "ancestor::div[@class='cart_item_label']//button")).click();   //почему не находит с 1 слешом перед баттоном если баттон прямо в диве?
    }

    public boolean isItemIntoTheCart(String item) {
        try {
            driver.findElement(By.xpath("//*[contains(text(),'" + item + "')]"));
        } catch (NoSuchElementException exeption) {
            return false;
        }
        return true;
    }

    public boolean isItemRemoved(String item) {
        return !isItemIntoTheCart(item);
    }

    public double getItemPriceFromTheCart(String item) {
        return Double.parseDouble(driver.findElement(By.xpath("//*[contains(text(),'" + item + "')]/" +
                "ancestor::div[@class='cart_item_label']//*" +
                "[@class='inventory_item_price']")).getText().substring(1));
    }

}
