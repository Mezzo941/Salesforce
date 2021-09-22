package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CompletePage extends BasePage {

    private static final By COMPLETE_MSG = By.className("complete-header");

    public CompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isCompleteMsgShow() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(COMPLETE_MSG));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

}
