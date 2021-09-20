package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOnePage extends BasePage{

    static final By FIRST_NAME = By.id("first-name");
    static final By LAST_NAME = By.id("last-name");
    static final By POSTAL_CODE = By.id("postal-code");
    static final By CONTINUE_BUTTON = By.id("continue");

    public CheckoutStepOnePage(WebDriver driver) {
        super(driver);
    }

    public void enterCheckoutInfo(String firstName, String lastName, String postalCode){
        driver.findElement(FIRST_NAME).sendKeys(firstName);
        driver.findElement(LAST_NAME).sendKeys(lastName);
        driver.findElement(POSTAL_CODE).sendKeys(postalCode);
        driver.findElement(CONTINUE_BUTTON).click();
    }

}
