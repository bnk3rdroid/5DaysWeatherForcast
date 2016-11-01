package bnk3r.droid.weatherforecast.features.splashScreen.ui

/**
 * MVP contracts for the view and the presenter
 * Created by YomanHD on 01/11/2016.
 */
interface SplashScreenContract {
    interface View {
        fun showVersionName(versionName: String)
    }
    interface Presenter {
        fun retrieveVersionName()
    }
    interface DaggerComponent {
        fun splashScreenMVPComponent()
    }
}