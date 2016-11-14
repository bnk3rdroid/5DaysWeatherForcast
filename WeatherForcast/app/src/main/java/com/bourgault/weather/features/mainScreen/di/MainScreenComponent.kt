package com.bourgault.weather.features.mainScreen.di

import com.bourgault.weather.features.mainScreen.ui.MainScreenView
import com.bourgault.weather.modules.network.NetworkComponent
import dagger.Component

/**
 * Dagger2 component: MainScreen
 * Created by Yoann on 12/11/2016.
 */
@MainScreenScope
@Component(
        dependencies = arrayOf(NetworkComponent::class),
        modules = arrayOf(MainScreenModule::class)
)
interface MainScreenComponent {
    fun inject(activity: MainScreenView)
}