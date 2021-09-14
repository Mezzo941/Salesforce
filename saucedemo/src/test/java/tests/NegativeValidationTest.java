package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeValidationTest extends BaseTest {

    @Test
    public void LoginWithEmptyPasswordIsImpossible() {
        loginPage.open();
        loginPage.authorization("standard_user","");
        Assert.assertEquals(driver.findElement(NO_VALID_DATE_ERRORS).getText(), "Epic sadface: Password is required");
    }


    @Test
    public void LoginWithEmptyFieldsIsImpossible() {
        loginPage.open();
        loginPage.authorization("","");
        Assert.assertEquals(driver.findElement(NO_VALID_DATE_ERRORS).getText(), "Epic sadface: Username is required");
    }

    @Test
    public void LoginWithNotValidDateIsImpossible(){
        loginPage.open();
        loginPage.authorization("standard_user","standard_user");
        Assert.assertEquals(driver.findElement(NO_VALID_DATE_ERRORS).getText(), "Epic sadface: Username and password do not match any user in this service");
    }

}
