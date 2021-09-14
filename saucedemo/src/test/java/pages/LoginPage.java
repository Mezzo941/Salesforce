package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    static final By NO_VALID_DATE_DYNAMIC_ERRORS = By.cssSelector("[data-test=error]");

    private static final By USER_NAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void authorization(String user, String password) {
        driver.findElement(USER_NAME).sendKeys(user);
        driver.findElement(PASSWORD).sendKeys(password);
        driver.findElement(LOGIN_BUTTON).click();
    }


    public String getDynamicError()
    {
        return driver.findElement(NO_VALID_DATE_DYNAMIC_ERRORS).getText();
    }
}

