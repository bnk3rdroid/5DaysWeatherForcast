package com.bourgault.weather.modules.network.model

import java.util.*

/**
 * Request object
 * Created by Yoann on 12/11/2016.
 */
class WeatherRequestObject(
        var city: City,
        var cod: String,
        var message: Double,
        var cnt: Int,
        var list: ArrayList<WeatherDayObject>
) {
}