package com.bourgault.weather.modules.network.model

/**
 * Request object
 * Created by Yoann on 12/11/2016.
 */
class Main(
        var temp: Double,
        var temp_min: Double,
        var temp_max: Double,
        var pressure: Double,
        var sea_level: Double,
        var grnd_level: Double,
        var humidity: Int,
        var temp_kf: Double
) {
}