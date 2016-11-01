package bnk3r.droid.weatherforecast.features.splashScreen.di

import bnk3r.droid.weatherforecast.application.App
import bnk3r.droid.weatherforecast.features.splashScreen.ui.SplashScreenContract
import bnk3r.droid.weatherforecast.features.splashScreen.ui.SplashScreenPresenter
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module: SplashScreen
 * Created by YomanHD on 01/11/2016.
 */
@Module
class SplashScreenModule(private val view: SplashScreenContract.View) {

    @Provides @SplashScreenScope
    fun providesPresenter(app: App): SplashScreenContract.Presenter {
        return SplashScreenPresenter(app, view)
    }
}