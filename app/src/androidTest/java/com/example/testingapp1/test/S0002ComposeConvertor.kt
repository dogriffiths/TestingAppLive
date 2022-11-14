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

    val converterScreen = ConverterScreen()

    @Before
    fun setUp() {
        scenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun shouldDoAnEspressoConversionProperly() {
        with(converterScreen) {
            celsius.set("-40")
            convert.click()
            fahrenheit.matches("-40")
        }
    }
}

class ConverterScreen {
    val celsius get() = EditTextProxy(withId(id.celsius))
    val convert get() = EspressoProxy(withText("Convert"))
    val fahrenheit get() = EspressoProxy(withResourceName("fahrenheit"))
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