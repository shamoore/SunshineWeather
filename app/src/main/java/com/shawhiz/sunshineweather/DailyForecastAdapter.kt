package com.shawhiz.sunshineweather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shawhiz.sunshineweather.data.DailyForecast
import com.shawhiz.sunshineweather.databinding.ForecastDayItemBinding

/**
 * Created by Shannon Moore on 12/7/2021.
 */
class DailyForecastAdapter : ListAdapter<DailyForecast, DailyForecastAdapter.ViewHolder>(DailyForecastAdapterCallback()) {
    inner class ViewHolder(val binding: ForecastDayItemBinding) : RecyclerView.ViewHolder(binding.root)

    class DailyForecastAdapterCallback : DiffUtil.ItemCallback<DailyForecast>() {
        private fun areSame(oldItem: DailyForecast, newItem: DailyForecast) = oldItem == newItem
        override fun areItemsTheSame(oldItem: DailyForecast, newItem: DailyForecast) = areSame(oldItem, newItem)
        override fun areContentsTheSame(oldItem: DailyForecast, newItem: DailyForecast) = areSame(oldItem, newItem)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: ForecastDayItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.forecast_day_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val forecast = getItem(position)
        with(holder.binding){
            dailyForecast = forecast
        }
    }
}