package com.shawhiz.sunshineweather.utilities

import android.content.Context
import android.net.ConnectivityManager

/**
 * Created by Shannon Moore on 12/4/2021.
 */

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork
    return activeNetwork == null
}




