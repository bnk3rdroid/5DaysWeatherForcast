package com.bourgault.weather.features.detailScreen.ui

import android.app.Application
import com.bourgault.weather.R
import com.bourgault.weather.features.detailScreen.model.Day
import com.bourgault.weather.features.detailScreen.model.Element
import com.bourgault.weather.features.mainScreen.model.WeatherDay
import java.util.*
import javax.inject.Inject

/**
 * MVP presenter: DetailScreen
 * Created by Yoann on 12/11/2016.
 */
class DetailScreenPresenter
@Inject
constructor(
        private var view: DetailScreenContract.View,
        private var context: Application
) : DetailScreenContract.Presenter {

    private var day: Day? = null

    /*
    * DetailScreenContract.Presenter
    * */

    override fun formatDay(weatherDay: WeatherDay?) {
        day = Day()
        day?.day = weatherDay?.day
        day?.icon = weatherDay?.bitmapIcon
        day?.temperature = "${weatherDay?.highestTemperature}° / ${weatherDay?.lowestTemperature}°"
        day?.wind = "${weatherDay?.periods!![0].windSpeed} m/s"
        day?.humidity = "${weatherDay?.periods!![0].humidity} %"
        day?.cloudiness = "${weatherDay?.periods!![0].cloudiness} %"
        day?.pressure_sea = "${weatherDay?.periods!![0].pressure_sea} hPa"
        day?.pressure_ground = "${weatherDay?.periods!![0].pressure_ground} hPa"
        view.setIcon(day?.icon)
        view.dayFormatted(createRVElements())
    }

    fun createRVElements() : ArrayList<Element> {
        val elements = ArrayList<Element>()
        elements.add(Element(context.getString(R.string.temperature), day?.temperature))
        elements.add(Element(context.getString(R.string.vent), day?.wind))
        elements.add(Element(context.getString(R.string.humidity), day?.humidity))
        elements.add(Element(context.getString(R.string.cloudiness), day?.cloudiness))
        elements.add(Element(context.getString(R.string.sea_pressure), day?.pressure_sea))
        elements.add(Element(context.getString(R.string.ground_pressure), day?.pressure_ground))
        return elements
    }

}