package com.bourgault.weather.features.splashScreen.di

import android.app.Application
import com.android.volley.RequestQueue
import com.bourgault.weather.features.mainScreen.ui.MainScreenWeatherAdapter
import com.bourgault.weather.features.splashScreen.ui.SplashScreenContract
import com.bourgault.weather.features.splashScreen.ui.SplashScreenPresenter
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module: SplashScreen
 * Created by Yoann on 12/11/2016.
 */
@Module
class SplashScreenModule(private val view: SplashScreenContract.View) {

    @Provides @SplashScreenScope
    fun providesPresenter(application: Application): SplashScreenContract.Presenter {
        return SplashScreenPresenter(application, view)
    }

    @Provides @SplashScreenScope
    fun providesAdapter(requestQueue: RequestQueue): MainScreenWeatherAdapter {
        return MainScreenWeatherAdapter(requestQueue)
    }
}