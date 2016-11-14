package com.bourgault.weather.features.mainScreen.ui

import com.bourgault.weather.features.mainScreen.model.WeatherDay

/**
 * Interfaces for the MVP pattern
 * Created by Yoann on 12/11/2016.
 */
interface MainScreenContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showWeather(weatherDays: List<WeatherDay>)
        fun hideWeather()
        fun showError(errorMessage: String)
        fun hideError()
        fun showCityName(city: String)
    }

    interface Presenter {
        fun findWeather()
    }
}