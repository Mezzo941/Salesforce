package pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public Menu openMenu() {
        return new Menu(driver);
    }

    public abstract boolean isPageOpened();

}
