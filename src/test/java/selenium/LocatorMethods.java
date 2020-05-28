package selenium;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LocatorMethods {
    private static final Properties locatorId = new Properties();
    private static final Properties locatorType = new Properties();

    public static WebDriver driver;
    public static WebDriverWait wait;


//    @Before
    public void setDriver() throws IOException {
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver81ForMac");
        driver=new ChromeDriver();
        wait=new WebDriverWait(driver,10);
        initProperties();
         }

    private void initProperties() throws IOException {
        InputStream inputStream=getClass().getClassLoader().getResourceAsStream("locatorId.properties");
        locatorId.load(inputStream);

        InputStream inputStream2=getClass().getClassLoader().getResourceAsStream("locatorType.properties");
        locatorType.load(inputStream2);
    }

//    @After
    void quit() {
        driver.quit();
    }


    public static WebElement waitForPresence(By path){
        return (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(path));
    }

    public static WebElement locateElements(String typeOrId){
        String id = locatorId.getProperty(typeOrId);
        String type = locatorType.getProperty(typeOrId);

        WebElement element;

        switch (type){
            case "xpath":
                element = waitForPresence(By.xpath(id));
                break;
            case "id":
                element = waitForPresence(By.id(id));
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
}
