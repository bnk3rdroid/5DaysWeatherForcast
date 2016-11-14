package com.bourgault.weather.application.di

import android.app.Application
import dagger.Component
import javax.inject.Singleton

/**
 * Dagger2 component: App
 * Created by Yoann on 12/11/2016.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {
    fun application() : Application
}