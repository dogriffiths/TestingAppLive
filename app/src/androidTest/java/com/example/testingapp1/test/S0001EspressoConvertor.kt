package com.example.testingapp1.test

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.testingapp1.MainActivity
import org.junit.Test
import org.junit.runner.RunWith
import com.example.testingapp1.R.id
import org.hamcrest.Matcher
import org.junit.Before

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class S0001EspressoConvertor {
    lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun shouldDoAnEspressoConversionProperly() {
        // Given I have entered -40 into the celsius field
        onView(withId(id.celsius)).perform(replaceText("-40"))
        // When I click the 'Convert' button
//        onView(withText("Convert")).perform(click())
        EspressoProxy(withText("Convert")).click()
        // Then I will see -40 in the fahrenheit field
//        onView(withResourceName("fahrenheit"))
//            .check(ViewAssertions.matches(withText("-40")))
        EspressoProxy(withResourceName("fahrenheit")).matches("-40")
    }
}

class EditTextProxy(matcher: Matcher<View>): EspressoProxy(matcher) {
    fun set(s: String) {
        onView(matcher).perform(replaceText(s))
    }
}

open class EspressoProxy(val matcher: Matcher<View>) {
    fun click() {
        onView(matcher).perform(ViewActions.click())
    }

    fun matches(s: String) {
        onView(matcher)
            .check(ViewAssertions.matches(withText(s)))
    }
}