package pages;

import factory.Account;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import wrapers.Input;
import wrapers.SInput;

public class AccountPage extends BasePage {

    private static final By NEW_BUTTON = By.cssSelector("[title=New]");
    private static final By SAVE_BUTTON = By.cssSelector("[title=Save]");
    private static final By TITLE = By.xpath("//h2[contains(text(),'New Account')]");

    public AccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isPageOpened() {
        try {
            new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        } catch (TimeoutException exception) {
            return false;
        }
        return true;
    }

    private void clickNew(){
        driver.findElement(NEW_BUTTON).click();
    }

    private void clickSave(){
        driver.findElement(SAVE_BUTTON).click();
    }

    public AccountPage createNewAccount() {
        Account account = new Account();
        clickNew();
        new SInput(driver).setLabel("Account Name").typeText(account.getAccountName());
        new Input(driver).setLabel("Phone").typeText(account.getPhone());
        new Input(driver).setLabel("Fax").typeText(account.getFax());
        new Input(driver).setLabel("Website").typeText(account.getWebsite());
        new Input(driver).setLabel("Employees").typeText(account.getEmployees());
        new Input(driver).setLabel("Annual Revenue").typeText(account.getAnnualRevenue());
        clickSave();
        return this;
    }

}

