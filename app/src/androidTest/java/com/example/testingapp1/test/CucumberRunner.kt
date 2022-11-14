package com.example.testingapp1.test

import android.app.Application
import android.content.Context
import android.os.Bundle
import dagger.hilt.android.testing.HiltTestApplication
import io.cucumber.android.runner.CucumberAndroidJUnitRunner
import io.cucumber.junit.CucumberOptions
import java.io.File

@CucumberOptions(features = ["features"], strict = true)
class CucumberRunner : CucumberAndroidJUnitRunner() {
    override fun onCreate(bundle: Bundle) {
        bundle.putString("plugin", pluginConfigurationString)
        File(absoluteFilesPath).mkdirs()
        super.onCreate(bundle)
    }

    private val pluginConfigurationString: String
        private get() {
            val cucumber = "cucumber"
            val separator = "--"
            return "junit:" + getCucumberXml(cucumber) + separator +
                    "html:" + getCucumberHtml(cucumber)
        }

    private fun getCucumberHtml(cucumber: String): String {
        return "$absoluteFilesPath/$cucumber.html"
    }

    private fun getCucumberXml(cucumber: String): String {
        return "$absoluteFilesPath/$cucumber.xml"
    }

    /**
     * The path which is used for the report files.
     *
     * @return the absolute path for the report files
     */
    private val absoluteFilesPath: String
        private get() {

            // Stored testing device at /sdcard/Android/data/com.example.testingapp1
            val directory = targetContext.getExternalFilesDir(null)
            return File(directory, "reports").absolutePath
        }


    @Throws(ClassNotFoundException::class,
        IllegalAccessException::class,
        InstantiationException::class)
    override fun newApplication(cl: ClassLoader, className: String, context: Context): Application {
        return super.newApplication(cl, HiltTestApplication::class.java.name, context)
    }
}

