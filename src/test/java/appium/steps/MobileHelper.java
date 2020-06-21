package appium.steps;


import io.appium.java_client.MobileElement;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;


import static appium.steps.AppiumSetup.driver;
import static utils.Locator.*;
public class MobileHelper {

    public static void userPassScreenHelper(String activityName){
        ((AndroidDriver) driver).startActivity(new Activity("io.appium.android.apis", activityName));
    }

    public static void mobilePressBackHelper(){
        driver.navigate().back();
    }

    public static void mobilePressKeyHelper(String key){
        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.valueOf(key)));
    }

    public static void userShouldSeeHelper(String element){
        boolean displayed = locateElement(element).isDisplayed();
        Assert.assertEquals(true,displayed);
    }

    public static void mobileTextInShouldBeHelper(String element,String result){
        String expected = locateElement(element).getText();
        Assert.assertEquals(expected,result);
    }

    public static void navigateHelper(String url){
        driver.navigate().to(url);
    }

    public static void enterToAreaHelper(String input,String element){

        locateElement(element).sendKeys(input+ Keys.ENTER);
    }

    public static void clickHelper(String element){
        locateElement(element).click();
    }

    public static void shouldSeePageHelper(String element){
        boolean displayed = locateElement(element).isDisplayed();
        Assert.assertEquals(true,displayed);
    }

    public static void userViewsHelper(String element) {
        boolean displayed = locateElement(element).isDisplayed();
        Assert.assertEquals(true, displayed);
    }

    public static void moveToHelper(String element, String element2){
        WebElement element3 = locateElement(element2);
        WebElement element1 = locateElement(element);
        Actions action = new Actions(driver);

        action.moveToElement(element1).click().moveToElement(element3).build().perform();
    }

}
