package com.shawhiz.sunshineweather.forecast

import android.content.Context
import com.google.gson.Gson
import com.shawhiz.sunshineweather.data.Forecast
import com.shawhiz.sunshineweather.utilities.NetworkResult
import com.shawhiz.sunshineweather.utilities.NetworkSuccess

/**
 * Created by Shannon Moore on 12/8/2021.
 */
class MockForecastModel(private val context: Context) : ForecastModelInterface {

    override fun getForecast(cityId: String, units: String): NetworkResult<Forecast> {

        val response = Gson().fromJson(MockJson.mockGetForecast, Forecast::class.java)
        return NetworkSuccess(response)
    }
}