package bnk3r.droid.weatherforecast.features.mainScreen.di

import bnk3r.droid.weatherforecast.features.mainScreen.ui.MainScreenPresenter
import bnk3r.droid.weatherforecast.features.mainScreen.ui.MainScreenContract
import com.android.volley.RequestQueue
import dagger.Module
import dagger.Provides

/**
 * Dagger2 module: MainScreen
 * Created by YomanHD on 01/11/2016.
 */
@Module
class MainScreenModule(private val view: MainScreenContract.View) {

    @Provides @MainScreenScope
    fun providesPresenter(requestQueue: RequestQueue) : MainScreenContract.Presenter {
        return MainScreenPresenter(view, requestQueue)
    }

}