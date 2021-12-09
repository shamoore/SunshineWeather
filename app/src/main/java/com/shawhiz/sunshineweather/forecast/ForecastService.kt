package com.shawhiz.sunshineweather.forecast

import com.shawhiz.sunshineweather.data.Forecast
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Shannon Moore on 12/6/2021.
 */
interface ForecastService {
    @GET("/data/2.5/forecast/daily")
    fun pullForecast(
        @Query("q") cityId: String,
        @Query("cnt") count: String,
        @Query("mode") mode: String,
        @Query("units") units: String,
        @Query("apikey") apiKey: String,
    ): Call<Forecast>

    @GET("/data/2.5/forecast/daily")
    fun pullForecast(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("cnt") count: String,
        @Query("mode") mode: String,
        @Query("units") units: String,
        @Query("apikey") apiKey: String,
    ): Call<Forecast>
}