package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwoPage extends BasePage {

    private static final By TAX = By.cssSelector("[class=summary_tax_label]");
    private static final By TOTAL = By.cssSelector("[class=summary_total_label]");
    private static final By ITEM_TOTAL = By.cssSelector("[class=summary_subtotal_label]");
    private static final By FINISH_BUTTON = By.id("finish");

    public CheckoutStepTwoPage(WebDriver driver) {
        super(driver);
    }

    public Double getTax() {
        return Double.parseDouble(driver.findElement(TAX).getText().substring(6));
    }

    public Double getTotal() {
        return Double.parseDouble(driver.findElement(TOTAL).getText().substring(8));
    }

    public Double getItemTotal() {
        return Double.parseDouble(driver.findElement(ITEM_TOTAL).getText().substring(13));
    }

    public void completeOrder(){
        driver.findElement(FINISH_BUTTON).click();
    }

}
