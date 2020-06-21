package appium.steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigProperties;
import utils.VideoManager;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumSetup {
    ConfigProperties config= new ConfigProperties();
    public static AppiumDriver<MobileElement> driver;
    public static WebDriverWait wait;

    @Before
    public void setDriver() {
        try {
            config.initProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DesiredCapabilities caps=new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"9.0");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel API 28");
        caps.setCapability("appPackage",config.apps.getProperty("packageName"));
        caps.setCapability("appActivity",config.apps.getProperty("activity"));

        URL url= null;
        try {
            url = new URL("http://localhost:4723/wd/hub");
            //        following URLs doing same issue. you can prefer which u want
//        URL url2=new URL("http://127.0.0.1:4723/wd/hub");
//        URL url3=new URL("http://0.0.0.0:4723/wd/hub");

            driver=new AndroidDriver(url,caps);
            wait=new WebDriverWait(driver,10);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        VideoManager.startRecording();
    }



    @AfterStep
    public void test(Scenario scenario){
        final byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot,"image/png",scenario.getName());
//        to change size of the image
//        byte[] resizedImage= ImageUtils.scale(screenshot,200,300);
//        scenario.attach(resizedImage,"image/png",scenario.getName()+System.currentTimeMillis());
    }

    @After
    public void quit(Scenario scenario) {
        VideoManager.stopRecording(scenario.getName().replaceAll(" ","_"));
        driver.quit();
    }



}
