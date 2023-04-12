package com.example.weather.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.presentation.MyApp
import com.example.weather.presentation.viewmodel.WeatherViewModel
import com.example.weather.presentation.viewmodel.WeatherViewModelFactory
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: WeatherViewModel
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("StringFormatMatches")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            val appComponent = MyApp.get(this).appComponent
            val weatherRepository = appComponent.weatherRepository

            val viewModelFactory = WeatherViewModelFactory(weatherRepository)
            viewModel = ViewModelProvider(this, viewModelFactory).get(WeatherViewModel::class.java)

            viewModel.fetchWeather("London", getString(R.string.api_key))

            binding.editCityButton.setOnClickListener {
                val isEditing = binding.cityInput.visibility == View.VISIBLE
                if (isEditing) {
                    val cityName = binding.cityInput.text.toString().trim()
                    if (cityName.isNotEmpty()) {
                        Log.d("MainActivity", "Received weather: $cityName")
                        viewModel.fetchWeather(cityName, getString(R.string.api_key))
                        binding.cityInput.visibility = View.GONE
                        binding.cityName.visibility = View.VISIBLE
                        binding.editCityButton.setImageResource(R.drawable.ic_arrow_left)
                        binding.motionLayout.transitionToStart()
                    }
                } else {
                    binding.cityInput.visibility = View.VISIBLE
                    binding.cityName.visibility = View.GONE
                    binding.editCityButton.setImageResource(R.drawable.ic_check)
                    binding.motionLayout.transitionToEnd()
                }
            }

            viewModel.weather.observe(this) { weather ->
                binding.cityName.text = weather?.cityName ?: "N/A"
                binding.temperature.text = getString(
                    R.string.temperature_format,
                    (weather?.temperature?.minus(273.15)?.times(10)?.roundToInt()?.div(10.0)) ?: 0.0
                )

                binding.weatherDescription.text = weather?.description ?: "N/A"
                binding.humidityValue.text = weather?.humidity + "%" ?: "N/A"
                binding.windValue.text = weather?.wind + " m/s" ?: "N/A"

                if (weather != null) {
                    Glide.with(this)
                        .load(weather.iconUrl)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(binding.weatherIcon)
                } else {
                    binding.weatherIcon.setImageResource(R.drawable.ic_launcher_foreground)
                }
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }
}