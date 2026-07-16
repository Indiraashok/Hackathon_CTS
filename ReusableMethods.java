package utils;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class ReusableMethods {

    WebDriver driver;

    public ReusableMethods(WebDriver driver) {
        this.driver = driver;
    }

    // ✅ Validate Textbox
    public void validateTextbox(WebElement element) {

        Assert.assertTrue(element.isDisplayed(), "Textbox not visible");
        Assert.assertTrue(element.isEnabled(), "Textbox not enabled");

        element.clear();
        element.sendKeys("1000");

        String value = element.getAttribute("value");
        Assert.assertTrue(value.length() > 0, "Textbox not accepting input");
    }

    // ✅ Validate Slider
    public void validateSlider(WebElement slider) {

        Actions actions = new Actions(driver);

        int width = slider.getSize().width;

        actions.clickAndHold(slider)
                .moveByOffset(width / 2, 0)
                .release()
                .perform();

        Assert.assertTrue(true, "Slider worked");
    }

    // ✅ Scroll
    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,500)");
    }
}