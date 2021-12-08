package com.shawhiz.sunshineweather.forecast

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

    var cityId: String = "Atlanta"
    var units: MeasurementUnit = MeasurementUnit.Imperial

    val forecast = repository.forecast

    fun getForecast() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getForecast(cityId, units.value)
        }
    }
}

enum class MeasurementUnit(val value: String) {
    Imperial("imperial"),
    Metric("metric")
}