package pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected static final String BASE_URL = "https://www.saucedemo.com";

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

}
