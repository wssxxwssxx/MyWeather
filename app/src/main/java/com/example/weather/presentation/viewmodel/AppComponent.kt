package com.example.weather.presentation.viewmodel

import android.content.Context
import androidx.room.Room
import com.example.weather.data.local.AppDatabase
import com.example.weather.data.local.WeatherDao
import com.example.weather.data.remote.WeatherApi
import com.example.weather.data.remote.WeatherRemoteDataSource
import com.example.weather.data.repository.WeatherRepositoryImpl
import com.example.weather.domain.repositories.WeatherRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppComponent(context: Context) {

    private val appDatabase: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "weather_database"
    ).build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://api.openweathermap.org/data/2.5/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherDao: WeatherDao = appDatabase.weatherDao()

    val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)

    val weatherRemoteDataSource: WeatherRemoteDataSource = WeatherRemoteDataSource(weatherApi)

    val weatherRepository: WeatherRepository = WeatherRepositoryImpl(weatherRemoteDataSource, weatherDao, context)
}