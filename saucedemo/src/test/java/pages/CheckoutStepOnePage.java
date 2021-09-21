package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage {

    private static final By FIRST_NAME = By.id("first-name");
    private static final By LAST_NAME = By.id("last-name");
    private static final By POSTAL_CODE = By.id("postal-code");
    private static final By CONTINUE_BUTTON = By.id("continue");
    private static final By DYNAMIC_ERROR_MSG = By.cssSelector("[data-test=error]");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public void enterCheckoutInfoAndContinue(String firstName, String lastName, String postalCode) {
        driver.findElement(FIRST_NAME).sendKeys(firstName);
        driver.findElement(LAST_NAME).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

    public String getErrorMsg() {
        return driver.findElement(DYNAMIC_ERROR_MSG).getText();
    }

}
