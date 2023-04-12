package com.example.weather.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.weather.domain.repositories.WeatherRepository
import com.example.weather.domain.usecases.FetchWeatherUseCase

class WeatherViewModelFactory(private val weatherRepository: WeatherRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
            val fetchWeatherUseCase = FetchWeatherUseCase(weatherRepository)

            @Suppress("UNCHECKED_CAST")
            return WeatherViewModel(fetchWeatherUseCase) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}