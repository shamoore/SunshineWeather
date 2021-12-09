package com.shawhiz.sunshineweather.koin

import com.shawhiz.sunshineweather.forecast.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Shannon Moore on 12/7/2021.
 */

@JvmField
val appModule = module {

    //Forecast
    viewModel { ForecastViewModel() }
    factory<ForecastModelInterface> { ForecastModel(get()) }
    factory<ForecastRepositoryInterface> { ForecastRepository(get()) }
}