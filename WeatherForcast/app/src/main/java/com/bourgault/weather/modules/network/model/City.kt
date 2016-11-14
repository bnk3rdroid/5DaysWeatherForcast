package com.bourgault.weather.modules.network.model

/**
 * Request object
 * Created by Yoann on 12/11/2016.
 */
class City(
        var id: Int,
        var name: String,
        var coord: Coord,
        var country: String,
        var population: Int,
        var sys: Sys) {
}

