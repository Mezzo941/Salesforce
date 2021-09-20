package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class CartPage extends BasePage {

    private static final By CONTINUE_BUTTON = By.id("continue-shopping");
    private static final By CHECK_OUT_BUTTON = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void continueShopping(){
        driver.findElement(CONTINUE_BUTTON).click();
    };

    public void checkout(){
        driver.findElement(CHECK_OUT_BUTTON).click();
    };

    public void removeItemFromTheCart(String item) {
        driver.findElement(By.xpath("//*[contains(text(),'" + item + "')]/" +
                "ancestor::div[@class='cart_item_label']//button")).click();   //почему не находит с 1 слешом перед баттоном если баттон прямо в диве?
    }

    public boolean isItemRemoved(String item) {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return !isItemOnThePage(item);
    }

}
