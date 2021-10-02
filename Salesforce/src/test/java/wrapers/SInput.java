package wrapers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SInput extends BaseInput {

    private final static String SEARCH_INPUT = "//span[contains(text(),'%s')]/ancestor::div[contains(@class,'uiInput')]//input";

    public SInput(WebDriver driver) {
        super(driver);
    }

    @Override
    public void typeText(String text) {
        driver.findElement(By.xpath(String.format(SEARCH_INPUT, label))).sendKeys(text);
    }

    public void search(){
        //search
    }
}
