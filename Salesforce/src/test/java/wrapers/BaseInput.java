package wrapers;

import org.openqa.selenium.WebDriver;

public abstract class BaseInput {

    protected WebDriver driver;
    protected String label;

    public BaseInput(WebDriver driver) {
        this.driver = driver;
    }

    public BaseInput setLabel(String label) {
        this.label = label;
        return this;
    }

    public abstract void typeText(String text);

}
