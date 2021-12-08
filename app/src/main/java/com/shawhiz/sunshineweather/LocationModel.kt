package com.shawhiz.sunshineweather.data

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.location.LocationManager
import com.shawhiz.sunshineweather.permissionCheck

/**
 * Created by Shannon Moore on 12/4/2021.
 */

interface LocationModelInterface {
    fun getLocation(): Location?
}

class LocationModel(private val context: Context) : LocationModelInterface {

    private val permissions = arrayListOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION)
    private val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    @SuppressLint("MissingPermission")
    override fun getLocation(): Location? {
        return if (permissionCheck(context, permissions)) locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER) else null
    }
}


