package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @DataProvider(name = "noValidData")
    public Object[][] data() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "", "Epic sadface: Username is required"},
                {"standard_user","standard_user","Epic sadface: Username and password do not match any user in this service"},
        };
    }

    @Test
    public void ValidDataUserSuccessfulLogin() {
        loginPage.open();
        loginPage.authorization("standard_user", "secret_sauce");
        Assert.assertTrue(catalogPage.isOpened());
    }

    @Test
    public void lockedUserCantLogin() {
        loginPage.open();
        loginPage.authorization("locked_out_user", "secret_sauce");
        Assert.assertEquals(loginPage.getDynamicError(), "Epic sadface: Sorry, this user has been locked out.");
    }


    @Test(dataProvider = "noValidData")
    public void noValidDataInputBreakSuccess(String userName,String lastName, String error) {
        loginPage.open();
        loginPage.authorization(userName,lastName);
        Assert.assertEquals(loginPage.getDynamicError(), error);
    }


}
