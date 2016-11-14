package com.bourgault.weather.features.mainScreen.di

import android.app.Application
import com.android.volley.RequestQueue
import com.bourgault.weather.features.mainScreen.ui.MainScreenContract
import com.bourgault.weather.features.mainScreen.ui.MainScreenPresenter
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module: MainScreen
 * Created by Yoann on 12/11/2016.
 */
@Module
class MainScreenModule(private val view: MainScreenContract.View) {

    @Provides @MainScreenScope
    fun providesPresenter(context: Application, requestQueue: RequestQueue) : MainScreenContract.Presenter {
        return MainScreenPresenter(view, context, requestQueue)
    }

}