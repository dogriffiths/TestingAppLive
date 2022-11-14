package com.example.testingapp1.ui.converter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import java.lang.Double.parseDouble

private fun isNumeric(s: String): Boolean {
    var numeric = true

    try {
        val num = parseDouble(s)
    } catch (e: NumberFormatException) {
        numeric = false
    }
    return numeric
}

@Composable
fun ComposeConverter() {
    var celsius: String by rememberSaveable { mutableStateOf("") }
    var fahrenheit: Int by rememberSaveable { mutableStateOf(0) }
    Column {
        TextField(
            modifier = Modifier.testTag("celsius").fillMaxWidth(),
            value = celsius,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = {
                celsius = it
            },
        )
        Button(
            modifier = Modifier.testTag("convert"),
            onClick = {
                val celsiusInt = if (isNumeric(celsius)) celsius.toInt() else 0
                fahrenheit = (celsiusInt * 9 / 5) + 32
            }
        ) {
            Text("CONVERT")
        }
        Text(
            modifier = Modifier.testTag("fahrenheit"),
            text = fahrenheit.toString()
        )
    }
}