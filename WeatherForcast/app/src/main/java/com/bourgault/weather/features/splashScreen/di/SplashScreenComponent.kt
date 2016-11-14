package com.bourgault.weather.features.splashScreen.di

import com.bourgault.weather.application.di.AppComponent
import com.bourgault.weather.features.splashScreen.ui.SplashScreenView
import dagger.Component

/**
 * Dagger2 appComponent: SplashScreen
 * Created by Yoann on 12/11/2016.
 */
@SplashScreenScope
@Component(
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(SplashScreenModule::class)
)
interface SplashScreenComponent {
    fun inject(activity: SplashScreenView)
}