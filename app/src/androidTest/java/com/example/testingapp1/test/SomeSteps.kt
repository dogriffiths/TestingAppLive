package com.example.testingapp1.test

import android.content.Intent
import androidx.compose.ui.test.*
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import com.example.testingapp1.MainActivity
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import com.example.testingapp1.R.id
import io.cucumber.datatable.DataTable
import junit.framework.Assert.assertEquals
import org.hamcrest.Matchers.allOf
import kotlin.math.exp

var navController: NavController? = null

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
        getCurrentActivity()?. let {
            navController = Navigation.findNavController(
                it, id.nav_host_fragment_activity_main
            )
        }
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
        assertEquals(
            id.navigation_todos,
            navController?.currentDestination?.id
        )
    }

    @Then("I will the following todos")
    fun iCanSee(dataTable: DataTable) {
        val asMaps = dataTable.asMaps()
        asMaps.forEachIndexed { pos, rowMap ->
            val expectedName = rowMap["Name"]
            val expectedComplete = (rowMap["Complete"] == "TRUE")
            val rowMatcher = withRecyclerView(id.todos_list)
                .atPosition(pos)
            onView(
                allOf(
                    withId(id.todo_view_name),
                    isDescendantOfA(rowMatcher)
                )
            ).check(ViewAssertions.matches(withText(expectedName)))
            onView(
                allOf(
                    withId(id.todo_view_complete),
                    isDescendantOfA(rowMatcher)
                )
            ).check(ViewAssertions.matches(
                if (expectedComplete) isChecked() else isNotChecked()
            ))
        }
        onView(withRecyclerView(id.todos_list).atPosition(asMaps.size))
            .check(doesNotExist())
    }

    /*
          @When("^I will see the following todos$")
      fun iWillSeeTheFollowingCompose(dataTable: DataTable) {
          val asMaps = dataTable.asMaps()
          asMaps.forEachIndexed { pos, row ->
              val expectedName = row["Name"]
              val expectedComplete = row["Complete"]
              val rowMatcher = hasTestTag("Row-$pos")
              onNode(rowMatcher, true).performScrollTo()
              val nameMatcher = hasTestTag("name").and(hasAnyAncestor(rowMatcher))
              val completeMatcher = hasTestTag("complete").and(hasAnyAncestor(rowMatcher))
              onNode(nameMatcher, true).assertTextEquals(expectedName ?: "")
              if (expectedComplete == "TRUE") {
                  onNode(completeMatcher).assertIsOn()
              } else {
                  onNode(completeMatcher).assertIsOff()
              }
          }
          onNode(hasTestTag("Row-${asMaps.size}")).assertDoesNotExist()
      }
     */
}