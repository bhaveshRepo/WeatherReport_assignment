package com.example.weatherreport.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.weatherreport.R
import com.example.weatherreport.WeatherViewModel
import com.example.weatherreport.WeatherViewModelProviderFactory
import com.example.weatherreport.model.Weather
import com.example.weatherreport.repository.WeatherRepository

class MainActivity : AppCompatActivity() {

    lateinit var activityViewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = WeatherRepository()
        val weatherViewModelProviderFactory = WeatherViewModelProviderFactory(repository)
        activityViewModel = ViewModelProvider(this,weatherViewModelProviderFactory)
            .get(WeatherViewModel::class.java)

        setContentView(R.layout.activity_main)
    }
}