package com.example.weather.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weather.domain.entities.Weather

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather")
    fun getWeather(): LiveData<Weather>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weather: Weather)
}
