package com.bourgault.weather.detailScreen.di

import android.app.Application
import com.bourgault.weather.features.detailScreen.ui.DetailScreenContract
import com.bourgault.weather.features.detailScreen.ui.DetailScreenPresenter
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module: DetailScreen
 * Created by Yoann on 12/11/2016.
 */
@Module
class DetailScreenModule(private val view: DetailScreenContract.View) {

    @Provides @DetailScreenScope
    fun providesPresenter(context: Application) : DetailScreenContract.Presenter {
        return DetailScreenPresenter(view, context)
    }
}