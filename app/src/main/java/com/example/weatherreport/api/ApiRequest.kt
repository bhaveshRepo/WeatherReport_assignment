package com.example.weatherreport.api

import com.example.weatherreport.model.WeatherResponse
import com.example.weatherreport.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiRequest {

    @GET("/forecast")
    suspend fun getForecast(
        @Query("q")
        city_name: String,
        @Query("appid")
        app_id : String = Constants.api_key

    ) : Response<WeatherResponse>

}