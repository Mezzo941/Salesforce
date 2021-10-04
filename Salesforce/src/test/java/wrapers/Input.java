package wrapers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Input extends BaseInput{

    //private final static String ADDITIONAL_INPUT = "//span[contains(text(),'%s')]/ancestor::div[@class='uiInput uiInputText uiInput--default uiInput--input']//input";
    private final static String INPUT = "//span[contains(text(),'%s')]/ancestor::div[@class='slds-form-element__control']//input";

    public Input(WebDriver driver) {
        super(driver);
    }

    @Override
    public void typeText(String text) {
        driver.findElement(By.xpath(String.format(INPUT,label))).sendKeys(text);
    }

}
