package com.example.weather.domain.usecases

import com.example.weather.domain.entities.Weather
import com.example.weather.domain.repositories.WeatherRepository

class FetchWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun execute(city: String, apiKey: String): Weather {
        return weatherRepository.fetchWeather(city, apiKey)
    }
}
