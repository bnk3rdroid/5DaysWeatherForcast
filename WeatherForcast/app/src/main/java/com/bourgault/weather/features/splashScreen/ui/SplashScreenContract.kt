package com.bourgault.weather.features.splashScreen.ui

/**
 * MVP contracts for the view and the presenter
 * Created by Yoann on 12/11/2016.
 */
interface SplashScreenContract {
    interface View {
        fun showVersionName(versionName: String)
    }

    interface Presenter {
        fun retrieveVersionName()
    }
}