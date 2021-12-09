package com.shawhiz.sunshineweather.forecast

import android.content.Context
import android.location.Location
import com.shawhiz.sunshineweather.data.Forecast
import com.shawhiz.sunshineweather.utilities.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Shannon Moore on 12/4/2021.
 */

interface ForecastModelInterface {
    fun getForecast(location: Location?, units: String): NetworkResult<Forecast>
}

class ForecastModel(private val context: Context) : ForecastModelInterface {
    companion object {
        const val defaultCity = "Atlanta"
        const val baseUrl = "https://api.openweathermap.org"
        const val apiKey = "3aa158b2f14a9f493a8c725f8133d704"
        const val count = "10"
        const val mode = "json"

        val timeoutUnit = TimeUnit.SECONDS
        const val timeout: Long = 15

    }

    private var service: ForecastService? = null

    private fun create(): ForecastService {
        val client = OkHttpClient().newBuilder()
            .readTimeout(timeout, timeoutUnit)
            .writeTimeout(timeout, timeoutUnit)
            .connectTimeout(timeout, timeoutUnit)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(ForecastService::class.java)
    }

    private fun getService(): ForecastService {
        return service ?: create()
    }


    override fun getForecast(location: Location?, units: String): NetworkResult<Forecast> {

        if (isNetworkAvailable(context)) {
            return NetworkError
        }

        return try {
            val response = if(location !=null) getService().pullForecast(location.latitude.toString(), location.longitude.toString(), count, mode, units, apiKey).execute()
            else getService().pullForecast(defaultCity, count, mode, units, apiKey).execute()
            if (response.isSuccessful && response.body() != null) {
                NetworkSuccess(response.body()!!)
            } else ApiError
        } catch (err: Exception) {
            ApiError
        }
    }
}