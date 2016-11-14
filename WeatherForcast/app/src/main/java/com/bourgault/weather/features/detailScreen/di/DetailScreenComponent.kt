package com.bourgault.weather.features.detailScreen.di

import com.bourgault.weather.application.di.AppComponent
import com.bourgault.weather.detailScreen.di.DetailScreenModule
import com.bourgault.weather.detailScreen.di.DetailScreenScope
import com.bourgault.weather.features.detailScreen.ui.DetailScreenView
import dagger.Component

/**
 * Dagger2 component: DetailScreen
 * Created by Yoann on 12/11/2016.
 */
@DetailScreenScope
@Component(
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(DetailScreenModule::class)
)
interface DetailScreenComponent {
    fun inject(activity: DetailScreenView)
}