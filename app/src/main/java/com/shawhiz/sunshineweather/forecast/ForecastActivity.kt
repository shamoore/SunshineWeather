package com.shawhiz.sunshineweather.forecast

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.shawhiz.sunshineweather.R
import com.shawhiz.sunshineweather.databinding.ForecastActivityLayoutBinding
import org.koin.android.ext.android.inject

/**
 * Created by Shannon Moore on 12/7/2021.
 */
class ForecastActivity : AppCompatActivity() {

    private lateinit var binding: ForecastActivityLayoutBinding
    private lateinit var adapter: DailyForecastAdapter

    private val viewModel: ForecastViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.forecast_activity_layout)
        adapter = DailyForecastAdapter()

        binding.dailyForecastRecycler.layoutManager = LinearLayoutManager(this)
        binding.dailyForecastRecycler.adapter = adapter

        viewModel.forecast.observe(this, { forecast ->
            binding.dailyForecast = forecast.list.first()
            binding.city = forecast.city
            forecast.list.first().humidity.toInt()

            adapter.submitList(forecast.list)


        })

        viewModel.getForecast()
    }


}