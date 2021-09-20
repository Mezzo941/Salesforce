package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected final By PAGES_MAIN_TITTLE = By.cssSelector(".title");

    protected static final String BASE_URL = "https://www.saucedemo.com";

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isOpened(String title) {
        return (driver.findElement(PAGES_MAIN_TITTLE).getText().equals(title));
    }

    public boolean isItemIntoThePage(String item) {
        try {
            driver.findElement(By.xpath("//*[contains(text(),'" + item + "')]"));
        } catch (NoSuchElementException exception) {
            return false;
        }
        return true;
    }

    public double getItemPriceFromThePage(String item) {
        double price;
        try {
            price = Double.parseDouble(driver.findElement(By.xpath("//*[contains(text(),'" + item + "')]/" +
                    "ancestor::div[@class='cart_item_label']//*" +
                    "[@class='inventory_item_price']")).getText().substring(1));
        } catch (NoSuchElementException exception) {
            price = 0.0;
        }
        return price;
    }
}
