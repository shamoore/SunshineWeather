package com.shawhiz.sunshineweather.forecast.koin

import com.shawhiz.sunshineweather.forecast.ForecastModelInterface
import com.shawhiz.sunshineweather.forecast.MockForecastModel
import org.koin.dsl.module

/**
 * Created by Shannon Moore on 12/8/2021.
 */
@JvmField
val testAppModule = module(override = true) {
    single<ForecastModelInterface> { MockForecastModel(get()) }

}

