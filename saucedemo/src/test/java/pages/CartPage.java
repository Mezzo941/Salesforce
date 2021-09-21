package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CartPage extends BasePage {

    private static final By CONTINUE_BUTTON = By.id("continue-shopping");
    private static final By CHECK_OUT_BUTTON = By.id("checkout");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void continueShopping() {
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public void checkout() {
        driver.findElement(CHECK_OUT_BUTTON).click();
    }

    public void removeItemFromTheCart(String item) {
        driver.findElement(By.xpath("//*[contains(text(),'" + item + "')]/" +
                "ancestor::div[@class='cart_item_label']//button")).click();   //почему не находит с 1 слешом перед баттоном если баттон прямо в диве?
    }

    public boolean isItemRemoved(String item) {
        try {
            new WebDriverWait(driver, 1).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[contains(text(),'" + item + "')]")));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    public boolean isCartEmpty() {
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        List<WebElement> items = driver.findElements(By.xpath("//*[@class='cart_item']"));
        if (items.size() == 0) {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return true;
        } else {
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            return false;
        }
    }

}
