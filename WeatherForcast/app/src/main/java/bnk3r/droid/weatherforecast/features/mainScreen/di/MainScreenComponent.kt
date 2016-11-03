package bnk3r.droid.weatherforecast.features.mainScreen.di

import bnk3r.droid.weatherforecast.features.mainScreen.ui.MainScreenView
import bnk3r.droid.weatherforecast.modules.network.NetworkComponent
import dagger.Component

/**
 * Dagger2 component: MainScreen
 * Created by YomanHD on 01/11/2016.
 */
@MainScreenScope
@Component(
        dependencies = arrayOf(NetworkComponent::class),
        modules = arrayOf(MainScreenModule::class)
)
interface MainScreenComponent {
    fun inject(activity: MainScreenView)
}