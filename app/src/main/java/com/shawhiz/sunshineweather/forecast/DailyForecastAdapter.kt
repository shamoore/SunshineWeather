package com.shawhiz.sunshineweather.forecast

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shawhiz.sunshineweather.R
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
        val context = holder.binding.itemContainer.context
        with(holder.binding) {
            dailyForecast = forecast
            first = position == 0
            weatherIcon.setImageDrawable(ContextCompat.getDrawable(context, getWeatherDrawable(forecast.weatherDetails.icon)))

            itemContainer.setOnClickListener {
                expandedWeather.visibility = if (expandedWeather.visibility == View.VISIBLE) View.GONE else View.VISIBLE
            }

        }
    }

    private fun getWeatherDrawable(icon: String): Int {
        return when (icon) {
            "01d" -> R.drawable.weather_sunny
            "02d" -> R.drawable.weather_cloudy
            "01n" -> R.drawable.weather_moon
            "02n" -> R.drawable.weather_cloudy_night
            "03d" -> R.drawable.weather_cloudy
            "04d" -> R.drawable.weather_cloudy
            "09d" -> R.drawable.weather_rainy
            "10d" -> R.drawable.weather_rainy_2
            "11d" -> R.drawable.weather_stormy
            "13d" -> R.drawable.weather_rainy_2
            "50d" -> R.drawable.weather_sunny
            else -> R.drawable.weather_sunny
        }
    }
}