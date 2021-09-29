package pages;

import factory.FakeAccountFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import wrapers.Input;
import wrapers.SInput;

public class NewAccountPage extends BasePage {

    private static final By NEW_BUTTON = By.cssSelector("[title=New]");
    private static final By SAVE_BUTTON = By.cssSelector("[title=Save]");
    private static final By TITLE = By.xpath("//h2[contains(text(),'New Account')]");

    public NewAccountPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage openPage() {
        return null;
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

    public NewAccountPage createNewAccount() {

        FakeAccountFactory account = new FakeAccountFactory();
        account.setRandomData();

        Input input = new Input(driver);
        SInput sInput = new SInput(driver);

        driver.findElement(NEW_BUTTON).click();

        sInput.setLabel("Account Name").typeText(account.getAccountName());
        input.setLabel("Phone").typeText(account.getPhone());
        input.setLabel("Fax").typeText(account.getFax());
        input.setLabel("Website").typeText(account.getWebsite());
        input.setLabel("Employees").typeText(account.getEmployees());
        input.setLabel("Annual Revenue").typeText(account.getAnnualRevenue());

        driver.findElement(SAVE_BUTTON).click();

        return this;
    }

}

