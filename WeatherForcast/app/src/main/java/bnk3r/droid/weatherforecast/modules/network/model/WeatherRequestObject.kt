package bnk3r.droid.weatherforecast.modules.network.model

import java.util.*

class WeatherRequestObject(
        var city: City,
        var cod: String,
        var message: Double,
        var cnt: Int,
        var list: ArrayList<WeatherDayObject>
) {
}