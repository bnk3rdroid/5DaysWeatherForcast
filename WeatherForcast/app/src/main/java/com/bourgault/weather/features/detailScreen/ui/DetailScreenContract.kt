package com.bourgault.weather.features.detailScreen.ui

import android.graphics.Bitmap
import com.bourgault.weather.features.detailScreen.model.Element
import com.bourgault.weather.features.mainScreen.model.WeatherDay
import java.util.*

/**
 * MVP contracts: DetailScreen
 * Created by Yoann on 12/11/2016.
 */
interface DetailScreenContract {
    interface View {
        fun setIcon(icon: Bitmap?)
        fun dayFormatted(elements: ArrayList<Element>)

    }
    interface Presenter {
        fun formatDay(weatherDay: WeatherDay?)

    }
}