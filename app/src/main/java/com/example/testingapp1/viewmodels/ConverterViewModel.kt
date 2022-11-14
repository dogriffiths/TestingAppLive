package com.example.testingapp1.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConverterViewModel : ViewModel() {
    val celsius = MutableLiveData("0")
    val fahrenheit = MutableLiveData("0")

    fun convert() {
        fahrenheit.postValue("" + (32 + ((celsius.value ?: "0").toInt() * 9 / 5)))
    }
}