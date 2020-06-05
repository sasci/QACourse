package appium.steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

public class AppiumSetup {

    private static final Properties locatorId = new Properties();
    private static final Properties locatorType = new Properties();
    private static final Properties apps=new Properties();

    public static AppiumDriver<MobileElement> driver;
    public static WebDriverWait wait;

    @Before
    public void setDriver() {
        try {
            initProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
        DesiredCapabilities caps=new DesiredCapabilities();
        caps.setCapability(MobileCapabilityType.PLATFORM_NAME,"Android");
        caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,"9.0");
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME,"UiAutomator2");
        caps.setCapability(MobileCapabilityType.DEVICE_NAME,"Pixel API 28");
        caps.setCapability("appPackage",apps.getProperty("packageName"));
        caps.setCapability("appActivity",apps.getProperty("activity"));

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
        new VideoManager().startRecording();
    }

    private void initProperties() throws IOException {
        InputStream inputStream=getClass().getClassLoader().getResourceAsStream("locatorId.properties");
        locatorId.load(inputStream);

        InputStream inputStream2=getClass().getClassLoader().getResourceAsStream("locatorType.properties");
        locatorType.load(inputStream2);

        InputStream inputStream3=getClass().getClassLoader().getResourceAsStream("apps.properties");
        apps.load(inputStream3);
    }

    @After
    public void quit(Scenario scenario) {
        if (scenario.isFailed()) {
            // Take a screenshot...
            final byte[] screenshot = driver.getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png",scenario.getName()+scenario.getLine().toString());
        }
        new VideoManager().stopRecording(scenario.getName());
        driver.quit();
    }

    public static MobileElement waitForPresence(By path){
        return (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(path));
    }

    public static List<WebElement> waitForPresences(By path){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(path));
    }


    public static MobileElement locateElement(String typeOrId){
        String id = locatorId.getProperty(typeOrId);
        String type = locatorType.getProperty(typeOrId);

        MobileElement element;

        switch (type){
            case "xpath":
                element = waitForPresence(By.xpath(id));
                break;
            case "id":
                element = waitForPresence(By.id(id));
                break;
            case "desc":
                element = waitForPresence(new MobileBy.ByAccessibilityId(id));
                break;
            case "name":
                element = waitForPresence(By.name(id));
                break;
            case "linktext":
                element = waitForPresence(By.linkText(id));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return element;
    }

    public static List<MobileElement> locateElementsWithoutWait(String typeOrId){
        String id = locatorId.getProperty(typeOrId);
        String type = locatorType.getProperty(typeOrId);

        List<MobileElement> elements;

        switch (type){
            case "xpath":
                elements = driver.findElements(By.xpath(id));
                break;
            case "id":
                elements = driver.findElements(By.id(id));
                break;
            case "desc":
                elements = driver.findElements(new MobileBy.ByAccessibilityId(id));
                break;
            case "name":
                elements = driver.findElements(By.name(id));
                break;
            case "linktext":
                elements = driver.findElements(By.linkText(id));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return elements;
    }

    public static List<WebElement> locateElementsWithWait(String typeOrId){
        String id = locatorId.getProperty(typeOrId);
        String type = locatorType.getProperty(typeOrId);

        List<WebElement> elements;

        switch (type){
            case "xpath":
                elements = waitForPresences(By.xpath(id));
                break;
            case "id":
                elements = waitForPresences(By.id(id));
                break;
            case "desc":
                elements = waitForPresences(new MobileBy.ByAccessibilityId(id));
                break;
            case "name":
                elements = waitForPresences(By.name(id));
                break;
            case "linktext":
                elements = waitForPresences(By.linkText(id));
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + type);
        }
        return elements;
    }


}
