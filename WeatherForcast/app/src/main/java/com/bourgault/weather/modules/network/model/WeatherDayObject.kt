package com.bourgault.weather.modules.network.model

/**
 * Request object
 * Created by Yoann on 12/11/2016.
 */
class WeatherDayObject(
        var dt: Long,
        var main: Main,
        var weather: List<Weather>,
        var clouds: Clouds,
        var wind: Wind,
        var rain: Rain,
        var sys: Sys,
        var dt_txt: String
) {

    class Sys(private var pod: String) {}
}