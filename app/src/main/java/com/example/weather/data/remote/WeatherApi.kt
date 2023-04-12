package com.example.weather.data.remote

import com.example.weather.data.remote.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("weather")
    suspend fun getCurrentWeather(@Query("q") city: String, @Query("appid") apiKey: String): WeatherResponse
}