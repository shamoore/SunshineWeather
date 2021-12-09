package com.shawhiz.sunshineweather.forecast

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.LocationServices
import com.shawhiz.sunshineweather.R
import com.shawhiz.sunshineweather.databinding.ForecastActivityLayoutBinding
import org.koin.android.ext.android.inject

/**
 * Created by Shannon Moore on 12/7/2021.
 */

class ForecastActivity : AppCompatActivity() {
    companion object {
        const val permissionsRequest = 1218
    }

    private lateinit var binding: ForecastActivityLayoutBinding
    private lateinit var adapter: DailyForecastAdapter

    private val viewModel: ForecastViewModel by inject()

    override fun onResume() {
        super.onResume()
        getLocation()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.forecast_activity_layout)
        toggleProgress(true)
        adapter = DailyForecastAdapter()
        binding.dailyForecastRecycler.layoutManager = LinearLayoutManager(this)
        binding.dailyForecastRecycler.adapter = adapter

        viewModel.forecast.observe(this, { forecast ->
            toggleProgress(false)
            binding.dailyForecast = forecast.list.first()
            binding.city = forecast.city
            binding.descriptionIcon.setImageDrawable(ContextCompat.getDrawable(this, getWeatherDrawable(forecast.list.first().weatherDetails.icon)))
            forecast.list.first().humidity.toInt()
            adapter.submitList(forecast.list)
        })

        viewModel.location.observe(this, {
            viewModel.getForecast()
        })

    }

    private fun toggleProgress(show: Boolean) {
        binding.progressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun getLocation() {
        val client = LocationServices.getFusedLocationProviderClient(this)
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, listOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION).toTypedArray(), permissionsRequest)
        }

        client.lastLocation.addOnSuccessListener(this) { loc ->

            Toast.makeText(this, getString(if (loc != null) R.string.local_forecast else R.string.atlanta_forecast), Toast.LENGTH_SHORT).show()
            viewModel.location.postValue(loc)
        }
    }

    private fun getWeatherDrawable(icon: String): Int {
        return when (icon) {
            "01d" -> R.drawable.weather_sunny
            "02d" -> R.drawable.weather_cloudy
            "01n" -> R.drawable.weather_moon
            "02n" -> R.drawable.weather_cloudy_night
            "03d" -> R.drawable.weather_cloudy
            "04d" -> R.drawable.weather_cloudy
            "09d" -> R.drawable.weather_rainy
            "10d" -> R.drawable.weather_rainy_2
            "11d" -> R.drawable.weather_stormy
            "13d" -> R.drawable.weather_rainy_2
            "50d" -> R.drawable.weather_sunny
            else -> R.drawable.weather_sunny
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        getLocation()
    }

}


