package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CatalogPage extends BasePage{

    static final By CATALOG_PAGE_TITTLE = By.cssSelector(".title");

    public CatalogPage(WebDriver driver) {
        super(driver);
    }

    public Boolean isPageOpened() {
        return !driver.findElements(CATALOG_PAGE_TITTLE).isEmpty();
    }

}
