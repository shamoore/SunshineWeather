package com.shawhiz.sunshineweather

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import androidx.core.app.ActivityCompat

/**
 * Created by Shannon Moore on 12/4/2021.
 */

fun permissionCheck(context: Context, permissions: ArrayList<String>) =
    permissions.any { perm -> ActivityCompat.checkSelfPermission(context, perm) == PackageManager.PERMISSION_GRANTED }

fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = connectivityManager.activeNetwork
    return activeNetwork == null
}




