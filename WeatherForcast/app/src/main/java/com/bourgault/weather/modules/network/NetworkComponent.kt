package com.bourgault.weather.modules.network

import android.app.Application
import com.android.volley.RequestQueue
import com.bourgault.weather.application.di.AppComponent
import dagger.Component

/**
 * Dagger2 appComponent: Network
 * Created by Yoann on 12/11/2016.
 */
@NetworkScope
@Component (
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(NetworkModule::class)
)
interface NetworkComponent {
    fun requestQueue(): RequestQueue

    //Gives the context to dependant components
    fun application(): Application
}