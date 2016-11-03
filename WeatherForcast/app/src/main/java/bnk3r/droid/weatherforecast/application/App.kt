package bnk3r.droid.weatherforecast.application

import android.app.Application
import bnk3r.droid.weatherforecast.application.di.AppComponent
import bnk3r.droid.weatherforecast.application.di.AppModule
import bnk3r.droid.weatherforecast.application.di.DaggerAppComponent
import bnk3r.droid.weatherforecast.modules.network.DaggerNetworkComponent
import bnk3r.droid.weatherforecast.modules.network.NetworkComponent
import bnk3r.droid.weatherforecast.modules.network.NetworkModule

/**
 * Application class
 * Created by YomanHD on 01/11/2016.
 */
class App : Application() {

    lateinit var appComponent: AppComponent
    lateinit var networkComponent: NetworkComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        networkComponent = DaggerNetworkComponent.builder()
                .appComponent(appComponent)
                .networkModule(NetworkModule(this))
                .build()
    }
}