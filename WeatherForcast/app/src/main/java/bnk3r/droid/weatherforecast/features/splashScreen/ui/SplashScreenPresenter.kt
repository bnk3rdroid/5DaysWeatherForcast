package bnk3r.droid.weatherforecast.features.splashScreen.ui

import android.app.Application
import javax.inject.Inject

/**
 * MVP presenter
 * Created by YomanHD on 01/11/2016.
 */
class SplashScreenPresenter
@Inject
constructor(private var application: Application, private var view: SplashScreenContract.View)
: SplashScreenContract.Presenter {

    /*
    * SplashScreenContract.Presenter
    * */

    override fun retrieveVersionName() {
        view.showVersionName(application.packageManager.getPackageInfo(application.packageName, 0).versionName)
    }

}