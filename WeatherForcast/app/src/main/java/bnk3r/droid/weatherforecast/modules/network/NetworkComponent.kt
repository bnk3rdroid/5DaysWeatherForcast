package bnk3r.droid.weatherforecast.modules.network

import bnk3r.droid.weatherforecast.application.di.AppComponent
import bnk3r.droid.weatherforecast.features.mainScreen.ui.MainScreenPresenter
import bnk3r.droid.weatherforecast.features.mainScreen.ui.MainScreenView
import com.android.volley.RequestQueue
import dagger.Component

/**
 * Dagger2 appComponent: Network
 * Created by YomanHD on 02/11/2016.
 */
@NetworkScope
@Component (
        dependencies = arrayOf(AppComponent::class),
        modules = arrayOf(NetworkModule::class)
)
interface NetworkComponent {
    fun requestQueue(): RequestQueue
}