package com.example.testingapp1.test

import android.view.View
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
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
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class S0002ComposeConvertor {
    @get:Rule
    val testRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun shouldDoAComposeConversionProperly() {
        testRule.onNode(hasTestTag("celsius"))
            .performTextReplacement("-40")
        testRule.onNode(hasTestTag("convert"))
            .performClick()
        testRule.onNode(hasTestTag("fahrenheit"))
            .assert(hasText("-40"))
    }
}
