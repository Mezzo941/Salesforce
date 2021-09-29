package tests;

import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test
    public void createNewAccountIsSuccessful() {
        loginPage
                .openPage()
                .authorization("asking1234-p1ax@force.com", "e0bajj11")
                .openMenu()
                .Accounts()
                .createNewAccount();
    }



}
