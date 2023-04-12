package com.example.weather.data.remote.model

import com.example.weather.domain.entities.Weather
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val cityName: String,
    @SerializedName("main")
    val main: Main,
    @SerializedName("wind")
    val wind: Wind,
    @SerializedName("weather")
    val weatherList: List<WeatherInfo>
) {
    data class Main(
        @SerializedName("temp")
        val temperature: Double,
        @SerializedName("humidity")
        val humidity: Int
    )

    data class Wind(
        @SerializedName("speed")
        val speed: Double,
    )

    data class WeatherInfo(
        @SerializedName("description")
        val description: String,
        @SerializedName("icon")
        val icon: String
    )

    fun toWeatherEntity(): Weather {
        val weatherInfo = weatherList.firstOrNull()
        val iconUrl = "https://openweathermap.org/img/wn/${weatherInfo?.icon}.png"
        return Weather(id, cityName, main.temperature, weatherInfo?.description.orEmpty(), iconUrl, main.humidity.toString(),wind.speed.toString())
    }
}
