package com.shawhiz.sunshineweather.koin

import com.shawhiz.sunshineweather.forecast.ForecastRepository
import com.shawhiz.sunshineweather.forecast.ForecastRepositoryInterface
import com.shawhiz.sunshineweather.forecast.ForecastViewModel
import com.shawhiz.sunshineweather.forecast.ForecastModel
import com.shawhiz.sunshineweather.forecast.ForecastModelInterface
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Shannon Moore on 12/7/2021.
 */

@JvmField
val appModule = module {

    //Forecast
    viewModel { ForecastViewModel() }
    factory { ForecastModel(get()) as ForecastModelInterface }
    factory { ForecastRepository(get()) as ForecastRepositoryInterface }
}