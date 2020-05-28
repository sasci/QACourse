package appium.steps;


import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;


import static appium.steps.AppiumSetup.*;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofSeconds;

enum DIRECTION {
    DOWN, UP, LEFT, RIGHT;
}

public class ScrollSwipeActionsHelper {
    public static void touchActionSwipeHelper(String direction, String el1,String el2) {
        int size1 = 0;

        Dimension size = driver.manage().window().getSize();

        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;

        switch (DIRECTION.valueOf(direction)) {
            case RIGHT:
                startY = locateElement(el1).getLocation().getY();
                endY = locateElement(el1).getLocation().getY();
                startX = (int) (size.width * 0.85);
                endX = (int) (size.width * 0.15);
                break;

            case LEFT:
                startY = locateElement(el1).getLocation().getY();
                endY = locateElement(el1).getLocation().getY();
                startX = (int) (size.width * 0.05);
                endX = (int) (size.width * 0.90);
                break;

            case UP:
                startY = (int) (size.height * 0.30);
                endY = (int) (size.height * 0.70);
                startX = locateElement(el1).getLocation().getX();
                endX = locateElement(el1).getLocation().getX();
                break;

            case DOWN:
                startY = (int) (size.height * 0.70);
                endY = (int) (size.height * 0.30);
                startX = locateElement(el1).getLocation().getX();
                endX = locateElement(el1).getLocation().getX();
                break;
        }

        while(size1<1) {
            getAction(startY, endY, startX, endX);
            size1 = locateElementsWithoutWait(el2).size();
        }
    }


    public static void iterateAction(String el1,int number){

        final Point location = locateElement(el1).getLocation();
        int startY = location.getY();
        int endY = location.getY();
        final Dimension size = driver.manage().window().getSize();
        int startX = (int)(size.getWidth()*0.80);
        int endX = (int)(size.getWidth()*0.20);
        for(int i=1; i<=number;i++){
            getAction(startY, endY, startX, endX);
        }
    }

    private static void getAction(int startY, int endY, int startX, int endX) {
        new TouchAction<>(driver).press(point(startX,startY))
                .waitAction(waitOptions(ofSeconds(1)))
                .moveTo(point(endX,endY)).release().perform();
    }



    public static void touchActionMoveToElementHelper(String el1, String el2){
        final WebElement ele1 = locateElement(el1);
        final WebElement ele2 = locateElement(el2);
        TouchAction action=new TouchAction(driver);
        action.press(element(ele1)).waitAction(waitOptions(ofSeconds(1)))
                .moveTo(element(ele2)).release().perform();
    }

    public static void scrollToWithIdHelper(String text,String uiselector){
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\""+uiselector+"\")).scrollIntoView("
                        + "new UiSelector().text(\""+text+"\"));"));
    }

    public static void scrollToWithIdAndClickHelper(String text,String uiselector){
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().resourceId(\""+uiselector+"\")).scrollIntoView("
                        + "new UiSelector().text(\""+text+"\"));")).click();
    }

    public static void scrollToHelper(String text){
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollTextIntoView(\""+text+"\")"));
//        driver.findElement(MobileBy.AndroidUIAutomator(
//                "new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView("
//                        + "new UiSelector().text(\""+text+"\"));"));


    }

    public static void scrollHorizontalHelper(String text){
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".setAsHorizontalList().scrollTextIntoView(\""+text+"\")"));
//        driver.findElement(MobileBy.AndroidUIAutomator(
//                "new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList().scrollIntoView("
//                        + "new UiSelector().text(\""+text+"\"))"));
//        driver.findElement(MobileBy.AndroidUIAutomator(
//                "new UiScrollable(new UiSelector().resourceId(\"android:id/tabs\")).setAsHorizontalList().scrollIntoView("
//                        + "new UiSelector().text(\""+text+"\"))"));
    }


}
