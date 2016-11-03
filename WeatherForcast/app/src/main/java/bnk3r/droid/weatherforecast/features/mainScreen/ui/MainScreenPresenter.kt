package bnk3r.droid.weatherforecast.features.mainScreen.ui

import bnk3r.droid.weatherforecast.features.mainScreen.model.WeatherDay
import com.android.volley.RequestQueue
import java.util.*
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
        view.showWeather(fakeData())
    }

    private fun fakeData(): List<WeatherDay> {
        val list = ArrayList<WeatherDay>()
        var i: Int = 0
        while (i < 10) {
            list.add(WeatherDay())
            ++i
        }
        return list
    }

}