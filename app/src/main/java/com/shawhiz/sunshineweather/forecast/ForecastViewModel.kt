package com.shawhiz.sunshineweather.forecast

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shawhiz.sunshineweather.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

/**
 * Created by Shannon Moore on 12/7/2021.
 */
class ForecastViewModel : ViewModel(), KoinComponent {

    private val repository: ForecastRepositoryInterface by inject()
    val location = MutableLiveData<Location?>()

    var units: MeasurementUnit = MeasurementUnit.Imperial

    val forecast = repository.forecast

    fun getForecast() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getForecast(location.value, units.value)
        }
    }

    fun getWeatherDrawable(icon: String): Int {
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

enum class MeasurementUnit(val value: String) {
    Imperial("imperial"),
    Metric("metric")
}