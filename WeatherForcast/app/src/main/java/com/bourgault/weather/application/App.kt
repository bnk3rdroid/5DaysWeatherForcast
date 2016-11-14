package com.bourgault.weather.application

import android.app.Application
import com.bourgault.weather.application.di.AppComponent
import com.bourgault.weather.application.di.AppModule
import com.bourgault.weather.application.di.DaggerAppComponent
import com.bourgault.weather.modules.network.DaggerNetworkComponent
import com.bourgault.weather.modules.network.NetworkComponent
import com.bourgault.weather.modules.network.NetworkModule

/**
 * Application class
 * Created by Yoann on 12/11/2016.
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