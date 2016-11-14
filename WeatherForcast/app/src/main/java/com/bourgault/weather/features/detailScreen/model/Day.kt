package com.bourgault.weather.features.detailScreen.model

import android.graphics.Bitmap

/**
 * Bean for display: DetailScreenView
 * Created by Yoann on 12/11/2016.
 */
data class Day(
        var day: String? = null,
        var icon: Bitmap? = null,
        var temperature: String? = null,
        var wind: String? = null,
        var humidity : String? = null,
        var cloudiness: String? = null,
        var pressure_sea: String? = null,
        var pressure_ground: String? = null
) {

}