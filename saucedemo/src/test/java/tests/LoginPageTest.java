package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest{

    @Test
    public void ValidDataUserSuccessfulLogin(){
        loginPage.open();
        loginPage.authorization("standard_user","secret_sauce");
        Assert.assertTrue(catalogPage.isOpened("PRODUCTS"));
    }

    @Test
    public void lockedUserCantLogin(){
        loginPage.open();
        loginPage.authorization("locked_out_user","secret_sauce");
        Assert.assertEquals(loginPage.getDynamicError(), "Epic sadface: Sorry, this user has been locked out.");
    }


    @Test
    public void LoginWithEmptyPasswordIsImpossible() {
        loginPage.open();
        loginPage.authorization("standard_user","");
        Assert.assertEquals(loginPage.getDynamicError(), "Epic sadface: Password is required");
    }


    @Test
    public void LoginWithEmptyFieldsIsImpossible() {
        loginPage.open();
        loginPage.authorization("","");
        Assert.assertEquals(loginPage.getDynamicError(), "Epic sadface: Username is required");
    }

    @Test
    public void LoginWithNotValidDateIsImpossible(){
        loginPage.open();
        loginPage.authorization("standard_user","standard_user");
        Assert.assertEquals(loginPage.getDynamicError(), "Epic sadface: Username and password do not match any user in this service");
    }

}
