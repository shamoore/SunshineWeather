package com.shawhiz.sunshineweather.forecast

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private var units: MeasurementUnit = MeasurementUnit.Imperial

    val forecast = repository.forecast

    fun getForecast() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getForecast(location.value, units.value)
        }
    }
}

//TODO go back and add option for user to change measurement units
enum class MeasurementUnit(val value: String) {
    Imperial("imperial"),
    Metric("metric")
}