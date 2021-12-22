package com.example.weatherreport.api

import com.example.weatherreport.util.Constants
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiInstance {

    companion object{

        private val retrofit by lazy{
          val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)

          val client = OkHttpClient.Builder()
              .addInterceptor(logging)
              .build()

          val moshi = Moshi.Builder()
              .add(KotlinJsonAdapterFactory())
              .build()


          Retrofit.Builder()
              .baseUrl(Constants.base_url)
              .addConverterFactory(GsonConverterFactory.create())
              .client(client)
              .build()
        }

        val api by lazy {
            retrofit.create(ApiRequest::class.java)
        }

    }

}