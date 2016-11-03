package bnk3r.droid.weatherforecast.features.splashScreen.di

import bnk3r.droid.weatherforecast.application.di.AppComponent
import bnk3r.droid.weatherforecast.features.splashScreen.ui.SplashScreenView
import dagger.Component

/**
 * Dagger2 appComponent: SplashScreen
 * Created by YomanHD on 01/11/2016.
 */
@SplashScreenScope
@Component(
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(SplashScreenModule::class)
)
interface SplashScreenComponent {
    fun inject(activity: SplashScreenView)
}