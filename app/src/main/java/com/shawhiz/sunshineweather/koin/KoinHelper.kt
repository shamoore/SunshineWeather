package com.shawhiz.sunshineweather.koin

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.stopKoin

/**
 * Created by Shannon Moore on 12/7/2021.
 */
class KoinHelper {
    companion object {
        @JvmStatic
        fun startKoin(application: Application) {
            stopKoin()
            org.koin.core.context.startKoin {
                androidContext(application)
                modules(listOf(appModule))
            }
        }
    }
}