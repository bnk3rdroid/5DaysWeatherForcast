package bnk3r.droid.weatherforecast.modules.network

import bnk3r.droid.weatherforecast.application.di.AppComponent
import dagger.Component

/**
 * Dagger2 component: Network
 * Created by YomanHD on 02/11/2016.
 */
@NetworkScope
@Component
(
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(NetworkModule::class)
)
interface NetworkComponent {
}