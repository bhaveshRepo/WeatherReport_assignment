package com.example.weatherreport

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherreport.model.Weather
import com.example.weatherreport.model.WeatherModel
import com.example.weatherreport.model.WeatherResponse
import com.example.weatherreport.repository.WeatherRepository
import com.example.weatherreport.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class WeatherViewModel(val weatherRepository: WeatherRepository) : ViewModel(){

    val weatherResource : MutableLiveData<Resource<WeatherResponse>> = MutableLiveData()
    var weatherResponse: WeatherResponse? = null

    init {
        getData("London,uk")
    }
    fun getData(city_name : String){
        viewModelScope.launch {
            safeCallData(city_name)
        }
    }

    private suspend fun safeCallData(cityName: String) {
        weatherResource.postValue(Resource.Loading())
        try {
            val response = weatherRepository.getWeather(cityName)
            weatherResource.postValue(handleResponseToResource(response))
        } catch (t : Throwable){
            when(t){
                is IOException -> weatherResource.postValue(Resource.Error("Network Failure"))
                else -> weatherResource.postValue(Resource.Error(t.message.toString()))
            }
        }
    }

    private fun handleResponseToResource(response: Response<WeatherResponse>): Resource<WeatherResponse> {
            if(response.isSuccessful){
                response.body()?.let {
                    if(weatherResponse == null){
                    weatherResponse = it
                    }
                    return Resource.Success(weatherResponse ?: it)
                }
            }
        return Resource.Error(response.message())
    }
}