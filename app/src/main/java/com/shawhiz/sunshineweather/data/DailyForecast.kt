package com.shawhiz.sunshineweather.data

import java.util.*
import kotlin.math.roundToInt

class DailyForecast(
    val clouds: Int,
    val deg: Double,
    val dt: Int,
    val feels_like: FeelsLike,
    val gust: Double,
    val humidity: Double,
    val pop: Double,
    val pressure: Double,
    val rain: Double,
    val speed: Double,
    val sunrise: Int,
    val sunset: Int,
    val temp: Temp,
    private val weather: List<Weather>
) {
    val speedInt
        get() = speed.roundToInt()
    val humidityInt
        get() = humidity.roundToInt()
    val weekDayName: String
        get() = getWeekday()

    val windDirectionName: String
        get() = getWindDirection()

    val weatherDetails: Weather
        get() = weather.first()

    val feelsLikeTemp: Int
        get() = getFeelsLike()

    val currentTemp: Int
        get() = getCurrentTemperature()

    val pressureInt: Int
        get() = pressure.roundToInt()

    private fun getCurrentTemperature(): Int {
        val cal = Calendar.getInstance()
        cal.timeInMillis = dt.toLong()
        val temp = when (cal.get(Calendar.HOUR_OF_DAY)) {
            in 0..5 -> temp.night
            in 6..10 -> temp.morn
            in 11..17 -> temp.day
            else -> temp.eve
        }
        return temp.roundToInt()
    }

    private fun getFeelsLike(): Int {
        val cal = Calendar.getInstance()
        cal.timeInMillis = dt.toLong()
        val temp = when (cal.get(Calendar.HOUR_OF_DAY)) {
            in 0..5 -> feels_like.night
            in 6..10 -> feels_like.morn
            in 11..17 -> feels_like.day
            else -> feels_like.eve
        }
        return temp.roundToInt()
    }


    private fun getWeekday(): String {
        val cal = Calendar.getInstance()
        cal.timeInMillis = dt.toLong()
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG_FORMAT, Locale.ROOT) ?: ""
    }

    private fun getWindDirection(): String {
        val directions = arrayOf("N", "NW", "W", "SE", "S", "SE", "E", "NE")
        var angle = deg
        val index = ((if (360.also { angle %= it } < 0) angle + 360 else angle) / 45).toFloat().roundToInt() % 8
        return directions[index]
    }

}



