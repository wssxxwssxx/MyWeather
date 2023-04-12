package com.example.weather.util

import android.content.Context
import android.content.SharedPreferences

class AppPreferences(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)

    var selectedCity: String
        get() = sharedPreferences.getString("selected_city", "London") ?: "New York"
        set(value) = sharedPreferences.edit().putString("selected_city", value).apply()

    var apiKey: String
        get() = sharedPreferences.getString("api_key", "ApiKEY") ?: "ApiKEY"
        set(value) = sharedPreferences.edit().putString("api_key", value).apply()
}