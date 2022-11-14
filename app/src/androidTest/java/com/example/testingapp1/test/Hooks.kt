package com.example.testingapp1.test

import io.cucumber.java.After
import io.cucumber.java.Before
import okhttp3.mockwebserver.MockWebServer

var mockServer: MockWebServer? = null

class Hooks {
    @Before
    fun startServer() {
        mockServer = MockWebServer()
        mockServer?.start(8080)
    }

    @After
    fun stopServer() {
        mockServer?.shutdown()
    }
}