package bnk3r.droid.weatherforecast.application

import android.app.Application
import bnk3r.droid.weatherforecast.application.di.AppComponent
import bnk3r.droid.weatherforecast.application.di.AppModule
import bnk3r.droid.weatherforecast.application.di.DaggerAppComponent

/**
 * Application class
 * Created by YomanHD on 01/11/2016.
 */
class App: Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}