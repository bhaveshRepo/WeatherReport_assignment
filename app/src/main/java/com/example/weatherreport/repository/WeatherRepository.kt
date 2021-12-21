package com.example.weatherreport.repository

import com.example.weatherreport.api.ApiInstance

class WeatherRepository {

    suspend fun getWeather(city_name : String) = ApiInstance.api.getForecast(city_name)

}