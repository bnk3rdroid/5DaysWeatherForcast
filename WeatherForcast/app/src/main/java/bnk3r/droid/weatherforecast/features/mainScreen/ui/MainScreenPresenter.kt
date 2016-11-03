package bnk3r.droid.weatherforecast.features.mainScreen.ui

import com.android.volley.RequestQueue
import javax.inject.Inject

/**
 * MVP Presenter
 * Created by YomanHD on 01/11/2016.
 */
class MainScreenPresenter
@Inject
constructor(private var view: MainScreenContract.View, private var requestQueue: RequestQueue)
: MainScreenContract.Presenter {

    override fun findWeather() {
    }

}