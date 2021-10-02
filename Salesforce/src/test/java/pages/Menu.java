package pages;

import org.openqa.selenium.WebDriver;

public class Menu {

    private static final String DROPDOWN_MENU = "//a[@title='Accounts']";
    protected WebDriver driver;

    public Menu(WebDriver driver) {
        this.driver = driver;
    }

    public AccountPage Accounts() {
        driver.get("https://company-5a.lightning.force.com/lightning/o/Account/list?filterName=Recent");
        return new AccountPage(driver);
    }

    public AccountPage Contacts() {
        return new AccountPage(driver);
    }

    public void Opportunities() {
    }

    public void Cases() {
    }

}
