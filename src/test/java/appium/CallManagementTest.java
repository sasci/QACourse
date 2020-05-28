package appium;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.net.MalformedURLException;
import java.net.URL;

public class CallManagementTest {
    static AppiumDriver driver;
    static WebDriverWait wait;

    public static MobileElement waitForVisible(By by){
        wait=new WebDriverWait(driver,10);
        return (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public static void main(String... args) throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.APPLICATION_NAME, "Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, "9.0");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel API 28");
        caps.setCapability("appPackage", "com.google.android.dialer");
        caps.setCapability("appActivity", "com.android.dialer.main.impl.MainActivity");
        caps.setCapability("noReset",true);

        URL url = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(url, caps);

        waitForVisible(By.xpath("//*[@text='Contacts']")).click();
        waitForVisible(By.id("com.google.android.dialer:id/empty_list_view_action")).click();
        waitForVisible(By.xpath("//*[@text='First name']")).sendKeys("Appium");
        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.TAB));
        waitForVisible(By.xpath("//*[@text='Last name']")).sendKeys("Test");
        ((AndroidDriver<MobileElement>) driver).hideKeyboard();
//        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.BACK));
        waitForVisible(By.xpath("//*[@text='Phone']")).sendKeys("+449123123142");
        waitForVisible(By.id("com.android.contacts:id/editor_menu_save_button")).click();
        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.valueOf("APP_SWITCH")));
        Thread.sleep(1000);
        ((AndroidDriver<MobileElement>) driver).pressKey(new KeyEvent(AndroidKey.HOME));

    }
}
