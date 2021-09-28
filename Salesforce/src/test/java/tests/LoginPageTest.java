package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest{

    /* To easily log in later, save this URL:
    https://company-5a.my.salesforce.com

    Username:
    asking1234-p1ax@force.com

    Password:
    e0bajj11
    */

    @Test
    public void test1(){
        Assert.assertTrue(loginPage.openPage().isOpened());
        loginPage
                .openPage()
                .authorization("asking1234-p1ax@force.com","e0bajj11");
    }

}
