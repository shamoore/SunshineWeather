package com.shawhiz.sunshineweather.data

/**
 * Created by Shannon Moore on 12/6/2021.
 */
 data class Forecast(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<DailyForecast>,
    val message: Double
)

