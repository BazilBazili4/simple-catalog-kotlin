package com.example.catalog.ui.catalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CatalogViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = getGreet()
    }

    private val _secondField = MutableLiveData<String>().apply {
        value = "second"
    }
    val text: LiveData<String> = _text
    // TODO: Implement the ViewModel

    val secondField: LiveData<String> = _secondField

    fun getGreet(): String {
        return "Halo!"
    }

    fun updateGreet(flag: Boolean, string: String) {
        if (flag) {
            _secondField.value = "Zig!"
            _text.value = "Hi!$string"
        }
    }
}