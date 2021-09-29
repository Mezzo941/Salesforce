package pages;

import org.openqa.selenium.WebDriver;

public class Menu {

    private static final String DROPDOWN_MENU = "//a[@title='Accounts']";
    WebDriver driver;

    public Menu(WebDriver driver) {
        this.driver = driver;
    }

    public NewAccountPage Accounts() {
        driver.get("https://company-5a.lightning.force.com/lightning/o/Account/list?filterName=Recent");
        return new NewAccountPage(driver);
    }

    public void Contacts() {

    }

    public void Opportunities() {
    }

    public void Cases() {
    }

}
