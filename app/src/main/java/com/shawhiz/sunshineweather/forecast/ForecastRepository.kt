package com.shawhiz.sunshineweather.forecast

import android.location.Location
import androidx.lifecycle.MutableLiveData
import com.shawhiz.sunshineweather.data.Forecast
import com.shawhiz.sunshineweather.utilities.NetworkError
import com.shawhiz.sunshineweather.utilities.NetworkSuccess

/**
 * Created by Shannon Moore on 12/7/2021.
 */

interface ForecastRepositoryInterface {
    val forecast: MutableLiveData<Forecast>
    val error: MutableLiveData<NetworkError>
    fun getForecast(location: Location?, units: String)
}

class ForecastRepository(private val model: ForecastModelInterface) : ForecastRepositoryInterface {
    override val forecast = MutableLiveData<Forecast>()
    override val error = MutableLiveData<NetworkError>()

    override fun getForecast(location: Location?, units: String) {
        val response = model.getForecast(location, units)
        if (response is NetworkSuccess) forecast.postValue(response.data)
        else error.postValue(NetworkError)
    }
}