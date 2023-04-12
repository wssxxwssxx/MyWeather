package com.example.weather.domain.repositories

import androidx.lifecycle.LiveData
import com.example.weather.domain.entities.Weather

interface WeatherRepository {
    fun getWeather(): LiveData<Weather>
    suspend fun fetchWeather(city: String, apiKey: String): Weather
}
