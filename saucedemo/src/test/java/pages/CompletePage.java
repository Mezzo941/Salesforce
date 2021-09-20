package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompletePage extends BasePage{

    private static final By COMPLETE_MSG = By.className("complete-header");

    public CompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isCompleteMsgShow(){
        return driver.findElement(COMPLETE_MSG).isDisplayed();
    }

}
