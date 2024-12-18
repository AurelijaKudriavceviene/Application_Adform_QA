package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScrollingHelper {

    public static void scrollToBottom(WebDriver driver, int timeoutSeconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until((ExpectedCondition<Boolean>) wd -> {
            long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try {
                Thread.sleep(1000); // Allow the scroll animation to complete
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            return lastHeight == newHeight; // Check if the height hasn't changed
        });
    }

    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public static void scrollToText(WebDriver driver, String text, int timeoutSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));

        // Wait for an element containing the text to be present
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'" + text + "')]")));

        // Find the element containing the text
        WebElement elementWithText = driver.findElement(By.xpath("//*[contains(text(),'" + text + "')]"));

        // Scroll the element into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementWithText);
    }
}
