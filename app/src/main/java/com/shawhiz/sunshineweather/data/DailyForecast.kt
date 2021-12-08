package com.shawhiz.sunshineweather.data

import java.util.*
import kotlin.math.roundToInt

data class DailyForecast(
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
    val windDirection = getWindDirectionName()
    val weekday = getWeekdayName()
    val weatherDetails = weather[0]
    private fun getWeekdayName(): String? {
        val cal = Calendar.getInstance()
        cal.timeInMillis = dt.toLong()
        return cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.LONG_STANDALONE, Locale.ROOT)
    }

    private fun getWindDirectionName(): String {
        val directions = arrayOf("N", "NW", "W", "SE", "S", "SE", "E", "NE")
        var angle = deg
        val index = ((if (360.also { angle %= it } < 0) angle + 360 else angle) / 45).toFloat().roundToInt() % 8
        return directions[index]
    }
}