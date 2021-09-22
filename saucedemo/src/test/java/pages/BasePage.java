package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {

    protected static final By PAGES_MAIN_TITTLE = By.cssSelector(".title");

    protected static final String BASE_URL = "https://www.saucedemo.com";

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 3);
    }

    public Boolean isOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(PAGES_MAIN_TITTLE));
        String title = driver.findElement(PAGES_MAIN_TITTLE).getText();
        if (title.equals("PRODUCTS") && this instanceof CatalogPage) {
            return true;
        } else if (title.equals("YOUR CART") && this instanceof CartPage) {
            return true;
        } else if (title.equals("CHECKOUT: YOUR INFORMATION") && this instanceof CheckoutStepOnePage) {
            return true;
        } else if (title.equals("CHECKOUT: OVERVIEW") && this instanceof CheckoutStepTwoPage) {
            return true;
        } else return title.equals("CHECKOUT: COMPLETE!") && this instanceof CompletePage;
    }

    public boolean isItemOnThePage(String item) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + item + "')]")));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    public double getItemPriceFromThePage(String item) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'" + item + "')]/" +
                    "ancestor::div[@class='cart_item_label']//*" +
                    "[@class='inventory_item_price']")));
        } catch (TimeoutException exception) {
            return 0.0;
        }
        return Double.parseDouble(driver.findElement(By.xpath("//*[contains(text(),'" + item + "')]/" +
                "ancestor::div[@class='cart_item_label']//*" +
                "[@class='inventory_item_price']")).getText().substring(1));
    }

}
