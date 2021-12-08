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
class ForecastRepositoryTest : AutoCloseKoinTest() {

    companion object {
        const val testCity = "Atlanta"
        val testUnits = MeasurementUnit.Imperial
        const val expectedResults = 5
    }

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testObject: ForecastRepositoryInterface by inject()

    init {
        loadKoinModules(testAppModule)
    }

    @Test
    fun whenGetForecastCalledForecastIsPopulated() {
        val latch = CountDownLatch(1)
        val testForecast = testObject.forecast

        testForecast.observeForever {
            if (it != null) {
                latch.countDown()
            }
        }

        testObject.getForecast(testCity, testUnits.value)
        latch.await()

        assert(testForecast.value!!.list.size == expectedResults)
    }
}