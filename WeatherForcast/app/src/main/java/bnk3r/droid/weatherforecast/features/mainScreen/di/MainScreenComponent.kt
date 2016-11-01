package bnk3r.droid.weatherforecast.features.mainScreen.di

import bnk3r.droid.weatherforecast.features.mainScreen.ui.MainScreenView
import dagger.Component

/**
 * Dagger2 component: MainScreen
 * Created by YomanHD on 01/11/2016.
 */
@MainScreenScope
@Component(modules = arrayOf(MainScreenModule::class))
interface MainScreenComponent {
    fun inject(activity: MainScreenView)
}