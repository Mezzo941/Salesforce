package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    final By PAGES_MAIN_TITTLE = By.cssSelector(".title");

    protected static final String BASE_URL = "https://www.saucedemo.com";

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public Boolean isOpened() {
        return !driver.findElements(PAGES_MAIN_TITTLE).isEmpty();
    }

}
