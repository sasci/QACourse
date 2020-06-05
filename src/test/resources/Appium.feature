Feature: Testing apks dynamically independent from activity and package
  Scenario: user should proceed pages on appiumDemos animation activity
    #Given user install "appium_application" goes "Animation_page"
    When user clicks "FADE_IN"
    And user clicks "LIST_DIALOG"
    And user clicks "Command_ONE"
    Then user views "Command_ONE_Chosen"
    And user press key "BACK"
    And user press key "APP_SWITCH"
    And user press key "HOME"

  Scenario: user should switch between apps
    Given user opens "AppiumDemos"
    When user clicks "FADE_IN"
    And user clicks "LIST_DIALOG"
    And user clicks "Command_ONE"
    And user views "Command_ONE_Chosen"
    Then user press key "APP_SWITCH"
    And user clicks "Calculator"
    And user clicks "7"
    And user clicks "Plus"
    And user clicks "9"
    And user clicks "Equal"
    And text in "Result" should be "16"
    And user press key "BACK"

  Scenario: user should be able to scroll to the text
    Given user opens "AppiumDemos"
    And user clicks "Views"
    And user scroll to "Tabs"
    And user clicks "Tabs"
    And user clicks "Scrollable"
    And user scroll horizontal to "TAB 20"
    And user clicks "TAB_20"
    Then text in "Content_Of_Tab" should be "Content for tab with tag Tab 20"

  Scenario: user should swipe with TouchAction class
    Given user opens "AppiumDemos"
    And user clicks "Views"
    And user scroll to "Tabs"
    And user clicks "Tabs"
    And user clicks "Scrollable"
    And user scroll "TAB_5" to "TAB_1"
    And user clicks "TAB_8"

  Scenario: user should swipe with iteration TouchAction class
    Given user opens "AppiumDemos"
    And user clicks "Views"
    And user scroll to "Tabs"
    And user clicks "Tabs"
    And user clicks "Scrollable"
    And user scroll to "TAB_5" in 5 steps
    And user clicks "TAB_20"

  @PI2
  Scenario: user should swipe until element find using TouchAction class
    Given user opens "AppiumDemos"
    And user clicks "Views"
    And user scroll to "Tabs"
    And user clicks "Tabs"
    And user clicks "Scrollable"
    And user scroll to "RIGHT" direction at "TAB_5" until "TAB_20"
    And user clicks "TAB_20"

  Scenario: user should define the date from datepicker
    Given user opens "AppiumDemos"
    And user clicks "Views"
    And user clicks "Date_Widgets"
    And user clicks "Dialog"
    And user clicks "Change_Date"
    And user clicks "NEXT_MONTH"
    And user clicks "18_June"
    And user clicks "OK_Button"
    Then text in "Date_Display" should be "6-18-2020 01:02"

   Scenario: User tests in Real Device Calculator
     Given user opens "Calculator"
     And user clicks "7"
     And user clicks "Plus"
     And user clicks "9"
     And user clicks "Equal"
     Then text in "Result" should be "16"
     And user press key "BACK"

   Scenario: basic test
     Given user opens ""
     And user tap views

