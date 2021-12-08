package com.shawhiz.sunshineweather.forecast

import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.shawhiz.sunshineweather.ForecastApplication
import com.shawhiz.sunshineweather.forecast.koin.testAppModule
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.inject
import org.koin.test.AutoCloseKoinTest
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.util.concurrent.CountDownLatch

/**
 * Created by Shannon Moore on 12/8/2021.
 */
@RunWith(RobolectricTestRunner::class)
@Config(application = ForecastApplication::class, sdk = [Build.VERSION_CODES.O])
class ForecastViewModelTest : AutoCloseKoinTest() {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    init {
        loadKoinModules(testAppModule)
    }

    private val viewModel: ForecastViewModel by inject()

    @Test
    fun whenGetForecastCalledForecastIsPopulated() {
        val latch = CountDownLatch(1)
        viewModel.forecast.observeForever {
            if (it != null) {
                latch.countDown()
            }
        }

        viewModel.getForecast()
        latch.await()

        assert(viewModel.forecast.value!!.list.size == ForecastRepositoryTest.expectedResults)
    }
}