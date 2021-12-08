package com.shawhiz.sunshineweather.utilities

/**
 * Created by Shannon Moore on 12/7/2021.
 */
sealed class NetworkResult<out T : Any>
class NetworkSuccess<out T : Any>(val data: T) : NetworkResult<T>()
object NetworkError : NetworkResult<Nothing>()
object ApiError : NetworkResult<Nothing>()
