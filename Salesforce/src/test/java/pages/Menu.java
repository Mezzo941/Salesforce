package pages;

import org.openqa.selenium.WebDriver;

public class Menu {

    protected WebDriver driver;

    public Menu(WebDriver driver) {
        this.driver = driver;
    }

    public AccountPage Accounts() {
        driver.get("https://company-5a.lightning.force.com/lightning/o/Account/list?filterName=Recent");
        return new AccountPage(driver);
    }

}
