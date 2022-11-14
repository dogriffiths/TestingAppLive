package com.example.testingapp1.test

import android.app.Activity
import android.os.Looper
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage

fun getCurrentActivity(): Activity? {
    return if (Looper.myLooper() == Looper.getMainLooper()) {
        getCurrentActivityOnMainThread()
    } else {
        val topActivity = arrayOfNulls<Activity>(1)
        InstrumentationRegistry.getInstrumentation()
            .runOnMainSync { topActivity[0] = getCurrentActivityOnMainThread() }
        topActivity[0]
    }
}

private fun getCurrentActivityOnMainThread(): Activity? {
    val registry = ActivityLifecycleMonitorRegistry.getInstance()
    val activities = registry.getActivitiesInStage(Stage.RESUMED)
    return if (activities.iterator().hasNext()) activities.iterator().next() else null
}

