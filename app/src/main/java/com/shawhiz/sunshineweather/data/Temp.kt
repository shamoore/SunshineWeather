package com.shawhiz.sunshineweather.data

import kotlin.math.roundToInt

class Temp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
) {
    val maxInt: Int
        get() = max.roundToInt()

    val minInt: Int
        get() = min.roundToInt()
}