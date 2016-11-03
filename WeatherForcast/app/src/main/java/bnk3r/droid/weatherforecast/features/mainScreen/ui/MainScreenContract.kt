package bnk3r.droid.weatherforecast.features.mainScreen.ui

/**
 * Interfaces for the MVP pattern
 * Created by YomanHD on 01/11/2016.
 */
interface MainScreenContract {

    interface View {

    }

    interface Presenter {
        fun findWeather()
    }
}