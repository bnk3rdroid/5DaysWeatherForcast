package bnk3r.droid.weatherforecast.features.splashScreen.di

import android.app.Application
import bnk3r.droid.weatherforecast.features.mainScreen.ui.MainScreenWeatherAdapter
import bnk3r.droid.weatherforecast.features.splashScreen.ui.SplashScreenContract
import bnk3r.droid.weatherforecast.features.splashScreen.ui.SplashScreenPresenter
import com.android.volley.RequestQueue
import dagger.Module
import dagger.Provides
import java.util.*

/**
 * Dagger2 module: SplashScreen
 * Created by YomanHD on 01/11/2016.
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