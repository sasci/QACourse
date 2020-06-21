package utils;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static appium.steps.AppiumSetup.*;

public class Locator {

    static ConfigProperties config= new ConfigProperties();

    public static MobileElement waitForPresence(By path){
        return (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(path));
    }

    public static List<WebElement> waitForPresences(By path){
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(path));
    }


    public static MobileElement locateElement(String typeOrId){
        String id = config.locatorId.getProperty(typeOrId);
        String type = config.locatorType.getProperty(typeOrId);

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
        String id = config.locatorId.getProperty(typeOrId);
        String type = config.locatorType.getProperty(typeOrId);

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
        String id = config.locatorId.getProperty(typeOrId);
        String type = config.locatorType.getProperty(typeOrId);

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
