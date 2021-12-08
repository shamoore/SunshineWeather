package com.shawhiz.sunshineweather

import android.app.Application
import com.shawhiz.sunshineweather.koin.KoinHelper

/**
 * Created by Shannon Moore on 12/7/2021.
 */

class ForecastApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinHelper.startKoin(this)
    }
}