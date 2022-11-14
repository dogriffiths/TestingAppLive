package com.example.testingapp1.test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import com.example.testingapp1.R.id

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class S0001EspressoConvertor {
    @Test
    fun shouldDoAnEspressoConversionProperly() {
        // Given I have entered -40 into the celsius field
        onView(withId(id.celsius)).perform(replaceText("-40"))
        // When I click the 'Convert' button
        onView(withText("Convert")).perform(click())
        // Then I will see -40 in the fahrenheit field
    }
}
