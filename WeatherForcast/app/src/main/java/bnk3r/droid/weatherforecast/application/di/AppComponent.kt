package bnk3r.droid.weatherforecast.application.di

import bnk3r.droid.weatherforecast.application.App
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger2 component: App
 * Created by YomanHD on 01/11/2016.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun app() : App
}