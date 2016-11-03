package bnk3r.droid.weatherforecast.features.mainScreen.ui

import bnk3r.droid.weatherforecast.features.mainScreen.model.WeatherDay

/**
 * Interfaces for the MVP pattern
 * Created by YomanHD on 01/11/2016.
 */
interface MainScreenContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun showWeather(weatherDays: List<WeatherDay>)
        fun hideWeather()
        fun showError(errorMessage: String)
        fun hideError()
    }

    interface Presenter {
        fun findWeather()
    }
}