package com.bourgault.weather.modules.network.model

/**
 * Request object
 * Created by Yoann on 12/11/2016.
 */
class Weather(
        var id: Int,
        var main: String,
        var description: String,
        var icon: String) {
}