package com.example.weather.presentation

import android.app.Application
import android.content.Context
import com.example.weather.presentation.viewmodel.AppComponent
import com.example.weather.util.AppPreferences

class MyApp : Application() {

    lateinit var appComponent: AppComponent
        private set

    lateinit var appPreferences: AppPreferences
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = AppComponent(this)
        appPreferences = AppPreferences(this)
    }

    companion object {
        fun get(context: Context): MyApp {
            return context.applicationContext as MyApp
        }
    }
}