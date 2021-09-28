package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

    private final static String BASE_URL = "https://login.salesforce.com";
    private final static By LOGIN_FIELD = By.id("username");
    private final static By PASSWORD_FIELD = By.id("password");
    private final static By LOGGIN_BUTTON = By.id("Login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isOpened() {
        try {
            new WebDriverWait(driver,3).until(ExpectedConditions.visibilityOfElementLocated(LOGGIN_BUTTON));
        }
        catch(TimeoutException ex) {
            return false;
        }
        return true;
    }

    public LoginPage openPage(){
        driver.get(BASE_URL);
        return this;
    }

    public void authorization(String userName, String password){
        driver.findElement(LOGIN_FIELD).sendKeys(userName);
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
        driver.findElement(LOGGIN_BUTTON).click();
    }

}
