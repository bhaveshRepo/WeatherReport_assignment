package com.example.weatherreport.model

data class WeatherResponse(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherModel>,
    val message: Int
)