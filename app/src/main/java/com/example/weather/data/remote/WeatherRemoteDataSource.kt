package com.example.weather.data.remote

import com.example.weather.data.remote.model.WeatherResponse

class WeatherRemoteDataSource(private val weatherApi: WeatherApi) {
    suspend fun getCurrentWeather(city: String, apiKey: String): WeatherResponse {
        return weatherApi.getCurrentWeather(city, apiKey)
    }
}
