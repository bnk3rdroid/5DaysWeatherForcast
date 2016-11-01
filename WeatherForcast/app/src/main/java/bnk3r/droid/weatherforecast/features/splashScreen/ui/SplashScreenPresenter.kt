package bnk3r.droid.weatherforecast.features.splashScreen.ui

import bnk3r.droid.weatherforecast.application.App
import javax.inject.Inject

/**
 * MVP presenter
 * Created by YomanHD on 01/11/2016.
 */
class SplashScreenPresenter
@Inject constructor(private var app: App, private var view: SplashScreenContract.View)
: SplashScreenContract.Presenter {

    /*
    * SplashScreenContract.Presenter
    * */

    override fun retrieveVersionName() {
        view.showVersionName(app.packageManager.getPackageInfo(app.packageName, 0).versionName)
    }

}