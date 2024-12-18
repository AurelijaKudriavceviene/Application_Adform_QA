
package pageobject;
import helpers.ScrollingHelper;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class CareersPage extends BasePage {
    public CareersPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = "h1[class='scroll-margin']")
    private WebElement areYouReadyElement;

    @FindBy(css = "input[type='text'].form-control")
    private WebElement searchingInputElement;

    @FindBy(css = ".input-group-btn")
    private WebElement searchBtnElement;

    @FindBy(css = ".dataCell")
    private List<WebElement> jobElements;

    public boolean hasAJobTitle(String jobTitle) {
        waitForVisibilityOf(areYouReadyElement);
        ScrollingHelper.scrollToElement(driver, areYouReadyElement);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust timeout as needed
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("iframe#FairsailApplicantPortalIframe"))); // Replace with your iframe locator

        driver.switchTo().frame("FairsailApplicantPortalIframe");

        waitForVisibilityOf(searchingInputElement);

        searchingInputElement.sendKeys(jobTitle);
        searchBtnElement.click();

        for (WebElement jobElement : jobElements) {
            System.out.println("=== TITLE: " + jobElement.getText());
            String exjobTitle = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].textContent;", jobElement);
            System.out.println("++++ TT: " + jobTitle);
            if (exjobTitle != null && exjobTitle.contains(jobTitle)) {
                return true;
            }
        }
        return false;
    }
}
