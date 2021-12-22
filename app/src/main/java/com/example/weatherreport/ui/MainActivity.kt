package com.example.weatherreport.ui

import android.nfc.Tag
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherreport.R
import com.example.weatherreport.WeatherAdapter
import com.example.weatherreport.WeatherViewModel
import com.example.weatherreport.WeatherViewModelProviderFactory
import com.example.weatherreport.model.Weather
import com.example.weatherreport.repository.WeatherRepository
import com.example.weatherreport.util.Resource
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_weather.*
import kotlinx.android.synthetic.main.fragment_weather.paginationProgressBar

class MainActivity : AppCompatActivity() {
    private val TAG : String = "MainActivity"
    lateinit var activityViewModel: WeatherViewModel
    lateinit var weatherAdapter: WeatherAdapter

    private var countryName : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val repository = WeatherRepository()
        val weatherViewModelProviderFactory = WeatherViewModelProviderFactory(repository)
        activityViewModel = ViewModelProvider(this,weatherViewModelProviderFactory)
            .get(WeatherViewModel::class.java)
        setContentView(R.layout.activity_main)


        countryName = intent.getStringExtra("country")
        activityViewModel.getData(countryName!!)

        setUpRecyclerView()

        activityViewModel.weatherResource.observe(this) {
            when(it){
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let {
                            weatherResponse -> weatherAdapter.differ.submitList(weatherResponse.list.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let {
                        Toast.makeText(this,"Error Occurred : $it", Toast.LENGTH_LONG).show()
                        Log.e(TAG, it)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        }
    }

    private fun setUpRecyclerView() {
        weatherAdapter = WeatherAdapter()
        rv_mainActivity.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE

    }
    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE

    }
}