package com.example.testingapp1.test

import android.content.Intent
import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import com.example.testingapp1.MainActivity
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import com.example.testingapp1.R.id

class SomeSteps(
    val composeRuleHolder: ComposeRuleHolder,
    val scenarioHolder: ActivityScenarioHolder
): SemanticsNodeInteractionsProvider by composeRuleHolder.composeRule {

    val converterScreen = ConverterScreen()

    @Given("I have the launched the app")
    fun iLaunchTheThing() {
        scenarioHolder.launch(Intent(
            InstrumentationRegistry.getInstrumentation().targetContext,
            MainActivity::class.java
        ))
    }

    @Given("a celsius value of {int} has been entered")
    fun celsiusEntered(celsius: Int) {
        converterScreen.celsius.set("" + celsius)
    }

    @When("the convert button is tapped")
    fun convertTapped() {
        converterScreen.convert.click()
    }

    @Then("the fahrenheit value will be {int}")
    fun fIS(f: Int) {
        converterScreen.fahrenheit.matches("" + f)
    }

    @When("I tap to the TODOs option")
    fun iTapTODOS() {
        onView(withId(id.navigation_todos)).perform(click())
    }

    @Then("the TODOs screen will be visible")
    fun todosVisible() {

    }
}