package com.example.weather.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weather.domain.entities.Weather
import com.example.weather.domain.usecases.FetchWeatherUseCase
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val fetchWeatherUseCase: FetchWeatherUseCase
) : ViewModel() {
    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> = _weather

    fun fetchWeather(city: String, apiKey: String) {
        viewModelScope.launch {
            try {
                val fetchedWeather = fetchWeatherUseCase.execute(city, apiKey)
                _weather.postValue(fetchedWeather)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
