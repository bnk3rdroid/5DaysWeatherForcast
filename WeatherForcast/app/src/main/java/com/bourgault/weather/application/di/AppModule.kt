package com.bourgault.weather.application.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger2 module: App
 * Created by Yoann on 12/11/2016.
 */
@Module
class AppModule(private val application: Application) {

    @Provides @Singleton
    fun providesApplicationContext(): Application {
        return application
    }

}