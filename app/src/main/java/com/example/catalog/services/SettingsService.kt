package com.example.catalog.services

import android.content.Context
import android.content.Context.MODE_PRIVATE

import android.content.SharedPreferences

class SettingsService(
    val context: Context
) {
    private val DEFAULT_CITY = "defaultCity"

    fun setDefaultCity(city: String) {
        val pref = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        with (pref.edit()) {
            putString(DEFAULT_CITY, city)
            apply()
        }
    }

    fun getDefaultCity(): String? {
        val pref = context.getSharedPreferences("settings", Context.MODE_PRIVATE)
        return  pref.getString(DEFAULT_CITY, "")
    }
}