package com.example.weatherreport.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherreport.R
import com.example.weatherreport.WeatherAdapter
import com.example.weatherreport.WeatherViewModel
import com.example.weatherreport.model.Main
import com.example.weatherreport.util.Resource
import kotlinx.android.synthetic.main.fragment_weather.*


class WeatherFragment : Fragment(R.layout.fragment_weather) {

    lateinit var viewModel: WeatherViewModel
    lateinit var weatherAdapter: WeatherAdapter



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).activityViewModel
        setUpRecyclerView()

        viewModel.weatherResource.observe(viewLifecycleOwner, Observer {
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
                        Toast.makeText(context,"Error Occurred : $it",Toast.LENGTH_SHORT).show()
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }

        }
        )

    }

    private fun setUpRecyclerView() {
        weatherAdapter = WeatherAdapter()
        rv_weatherCity.apply {
            adapter = weatherAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE

    }
    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE

    }


}