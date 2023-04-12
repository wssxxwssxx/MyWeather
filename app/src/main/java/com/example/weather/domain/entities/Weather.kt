package com.example.weather.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class Weather(
    @PrimaryKey
    val id: Int,
    val cityName: String,
    val temperature: Double,
    val description: String,
    val iconUrl: String,
    val humidity: String,
    val wind: String
)
