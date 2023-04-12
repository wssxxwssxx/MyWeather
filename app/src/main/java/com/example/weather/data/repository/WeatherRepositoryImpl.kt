package com.example.weather.data.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.weather.data.local.WeatherDao
import com.example.weather.data.remote.WeatherRemoteDataSource
import com.example.weather.domain.entities.Weather
import com.example.weather.domain.repositories.WeatherRepository
import com.example.weather.presentation.MyApp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val weatherRemoteDataSource: WeatherRemoteDataSource,
    private val weatherDao: WeatherDao,
    private val context: Context
) : WeatherRepository {
    override fun getWeather(): LiveData<Weather> {
        val liveData = weatherDao.getWeather()
        return Transformations.map(liveData) {
            Log.d("WeatherRepository", "Loaded from database: $it")
            it
        }
    }

    override suspend fun fetchWeather(city: String, apiKey: String): Weather {
        val appPreferences = MyApp.get(context).appPreferences
        appPreferences.selectedCity = city
        appPreferences.apiKey = apiKey

        val response = weatherRemoteDataSource.getCurrentWeather(city, apiKey)
        Log.d("MainActivity", "Received weather: ${response.toWeatherEntity().temperature}")

        val weather = response.toWeatherEntity()

        withContext(Dispatchers.IO) {
            weatherDao.insertWeather(response.toWeatherEntity())
        }

        return weather
    }
}
