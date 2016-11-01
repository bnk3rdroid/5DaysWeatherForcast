package bnk3r.droid.weatherforecast.application.di

import bnk3r.droid.weatherforecast.application.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger2 module: App
 * Created by YomanHD on 01/11/2016.
 */
@Module
class AppModule(private val application: App) {

    @Provides @Singleton
    fun providesApplication(): App {
        return application
    }

}