package com.example.weatherreport

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherreport.model.WeatherModel
import kotlinx.android.synthetic.main.single_item.view.*

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private val diffCallBack = object : DiffUtil.ItemCallback<WeatherModel>() {
        override fun areItemsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem.dt == newItem.dt
        }

        override fun areContentsTheSame(oldItem: WeatherModel, newItem: WeatherModel): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,diffCallBack)

    inner class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val layoutHolder = LayoutInflater.from(parent.context)
            .inflate(R.layout.single_item,parent,false)
        return WeatherViewHolder(layoutHolder)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weatherItem = differ.currentList[position]
        holder.itemView.apply {
            tv_date.text = "Temprature = ${weatherItem.main?.temp.toString()}"
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}