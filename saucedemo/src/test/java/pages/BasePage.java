package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    protected static final By PAGES_MAIN_TITTLE = By.cssSelector(".title");

    protected static final String BASE_URL = "https://www.saucedemo.com";

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isOpened() {
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        String title = driver.findElement(PAGES_MAIN_TITTLE).getText();
        if (title.equals("PRODUCTS") && this instanceof CatalogPage) {
            return true;
        } else if (title.equals("YOUR CART") && this instanceof CartPage) {
            return true;
        }
        else if (title.equals("CHECKOUT: YOUR INFORMATION") && this instanceof CheckoutStepOnePage) {
            return true;
        }
        else if (title.equals("CHECKOUT: OVERVIEW") && this instanceof CheckoutStepTwoPage) {
            return true;
        }
        else if (title.equals("CHECKOUT: COMPLETE!") && this instanceof CompletePage) {
            return true;
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return false;
    }

    public boolean isItemOnThePage(String item) {
        try {
            driver.findElement(By.xpath("//*[contains(text(),'" + item + "')]"));
        } catch (NoSuchElementException exception) {
            return false;
        }
        return true;
    }

    public double getItemPriceFromThePage(String item) {
        try {
            return Double.parseDouble(driver.findElement(By.xpath("//*[contains(text(),'" + item + "')]/" +
                    "ancestor::div[@class='cart_item_label']//*" +
                    "[@class='inventory_item_price']")).getText().substring(1));
        } catch (NoSuchElementException exception) {
            return 0.0;
        }
    }

}
