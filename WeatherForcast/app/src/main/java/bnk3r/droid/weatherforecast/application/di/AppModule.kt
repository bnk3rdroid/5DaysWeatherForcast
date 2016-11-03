package bnk3r.droid.weatherforecast.application.di

import android.app.Application
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Dagger2 module: App
 * Created by YomanHD on 01/11/2016.
 */
@Module
class AppModule(private val application: Application) {

    @Provides @Singleton
    fun providesApplicationContext(): Application {
        return application
    }

}