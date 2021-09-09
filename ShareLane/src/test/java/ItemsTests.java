import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ItemsTests extends BaseTest {

    String zipCodeInput = "22222";
    String firstNameInput = "Seva";
    String lastNameInput = "Mironov";
    String emailInput = "jopa@bumagi.net";
    String passwordInput = "1234";

    String login, password;

    String authorNameMainPage, itemNameMainPage;
    String authorNameOffPage, itemNameOffPage;
    String authorNameShoppingCart, itemNameShoppingCart;

    void registration() { //use 1st

        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.findElement(By.linkText("Sign up")).click();
        driver.findElement(By.name("zip_code")).sendKeys(zipCodeInput);
        driver.findElement(By.cssSelector("[value=Continue]")).click();
        driver.findElement(By.name("first_name")).sendKeys(firstNameInput);
        driver.findElement(By.name("last_name")).sendKeys(lastNameInput);
        driver.findElement(By.name("email")).sendKeys(emailInput);
        driver.findElement(By.name("password1")).sendKeys(passwordInput);
        driver.findElement(By.name("password2")).sendKeys(passwordInput);
        driver.findElement(By.cssSelector("[value=Register]")).click();

        login = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[1]/td[2]/b")).getText();
        password = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[6]/td/table/tbody/tr[4]/td/table/tbody/tr[2]/td[2]")).getText();

    }

    void authorization() { //use 2nd

        driver.get("https://www.sharelane.com/cgi-bin/main.py");
        driver.findElement(By.name("email")).sendKeys(login);
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.cssSelector("[value=Login]")).click();

    }

    void addItemIntoTheBucket() {

        driver.get("https://www.sharelane.com/cgi-bin/main.py");

        authorNameMainPage = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[5]/td/table/tbody/tr/td[1]/table/tbody/tr[2]/td/p/b")).getText();
        itemNameMainPage = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[5]/td/table/tbody/tr/td[1]/table/tbody/tr[3]/td/a")).getText();

        driver.findElement(By.xpath("/html/body/center/table/tbody/tr[5]/td/table/tbody/tr/td[1]/table/tbody/tr[1]/td/a")).click();

        authorNameOffPage = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[5]/td/table/tbody/tr/td[2]/p[1]/b")).getText();
        itemNameOffPage = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[5]/td/table/tbody/tr/td[2]/p[2]")).getText();

        Assert.assertEquals(authorNameMainPage, authorNameOffPage);
        Assert.assertEquals(itemNameMainPage, itemNameOffPage);

        driver.findElement(By.xpath("//img[@src='../images/add_to_cart.gif']")).click();

    }

    @Test
    void ItemAddedIntoTheBucketBeingLogin() {

        registration();
        authorization();
        addItemIntoTheBucket();


        Assert.assertEquals(driver.findElement(By.className("confirmation_message")).getText(), "Book was added to the Shopping Cart");

        driver.findElement(By.xpath("//a[@href='./shopping_cart.py']")).click();

        authorNameShoppingCart = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[5]/td/table/tbody/tr[2]/td[1]")).getText();
        itemNameShoppingCart = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[5]/td/table/tbody/tr[2]/td[2]")).getText();

        Assert.assertEquals(authorNameShoppingCart, authorNameMainPage);
        Assert.assertEquals(itemNameShoppingCart, itemNameMainPage);

    }

    @Test
    void ItemNotAddedIntoTheBucketNotLogin() {
        addItemIntoTheBucket();
        Assert.assertEquals(driver.findElement(By.className("error_message")).getText(), "Oops, error. You must log in");
    }

}
