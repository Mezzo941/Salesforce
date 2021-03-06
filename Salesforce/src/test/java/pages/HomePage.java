package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends BasePage {

    private static final By HOME_PAGE = By.cssSelector("[class=flexipagePage]");

    public HomePage(WebDriver driver) {
        super(driver);
    }


    @Override
    public boolean isPageOpened() {
        try {
            new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(HOME_PAGE));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

}


