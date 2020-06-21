package appium.steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static appium.steps.MobileHelper.*;
import static appium.steps.ScrollSwipeActionsHelper.*;

public class MobileStepdefs {

    @When("user enters {string} to {string} area")
    public void userEntersToArea(String input, String area)
    {
        enterToAreaHelper(input, area);
    }

    @Then("user views {string}")
    public void userViews(String arg0) {
        userViewsHelper(arg0);
    }

    @And("user clicks {string}")
    public void userClicks(String element) {
        clickHelper(element);
    }

    @Then("user should see {string}")
    public void userShouldSee(String element) {
        userShouldSeeHelper(element);
    }

    @And("user move {string} to {string} area")
    public void userMoveToArea(String fileName, String area) {
        moveToHelper(fileName, area);
    }

    @And("user press back")
    public void userPressBack() {
        mobilePressBackHelper();
    }

    @And("user press key {string}")
    public void userPressKey(String arg0) {
        mobilePressKeyHelper(arg0);
    }

    @Given("user opens {string}")
    public void userOpens(String arg0) {
        System.out.println("user opened "+arg0);
    }

    @And("text in {string} should be {string}")
    public void textInShouldBe(String arg0, String arg1) {
        mobileTextInShouldBeHelper(arg0,arg1);
    }

    @And("user scroll to {string}")
    public void userScrollTo(String arg0) {scrollToHelper(arg0);
    }

    @And("user scroll horizontal to {string}")
    public void userScrollHorizontalTo(String arg0) {scrollHorizontalHelper(arg0);
    }

    @And("user scroll to {string} into {string}")
    public void userScrollToInto(String arg0, String arg1) {
        scrollToWithIdHelper(arg0,arg1);
    }

    @And("user scroll {string} to {string}")
    public void userSwipeToAndClick(String arg0, String arg1) {
        touchActionMoveToElementHelper(arg0,arg1);
    }

    @And("user scroll to {string} direction at {string} until {string}")
    public void userScrollToDirectionAt(String arg0, String arg1, String arg2) {
        touchActionSwipeHelper(arg0,arg1,arg2);
    }

    @And("user scroll to {string} in {int} steps")
    public void userScrollToInSteps(String arg0, int arg1) {
        iterateAction(arg0,arg1);
    }

    @Then("user pass {string} screen")
    public void userPassScreen(String activityName) {
        userPassScreenHelper(activityName);
    }
}
