package com.example.testingapp1.test

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario

class ActivityScenarioHolder {
    private var scenario: ActivityScenario<*>? = null

    fun launch(intent: Intent) {
        scenario = ActivityScenario.launch<Activity>(intent)
    }
}