
package pageobject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[@class='nav-link'][normalize-space()='Careers']")
    private WebElement careersBtnElement;

    public void goToCareersPage() {
        waitForVisibilityOf(cookiesBtnElement);
        cookiesBtnElement.click();
        careersBtnElement.click();
    }
}
