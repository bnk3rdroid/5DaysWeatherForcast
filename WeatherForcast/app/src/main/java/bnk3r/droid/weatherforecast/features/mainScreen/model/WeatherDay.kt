package bnk3r.droid.weatherforecast.features.mainScreen.model

import android.graphics.Bitmap
import java.util.*

/**
 * Bean for MainScreen's RecyclerView
 * Created by YomanHD on 03/11/2016.
 */
class WeatherDay {
    var city: String = ""
    var date: String = ""
    var lowestTemperature: Double = 0.0
    var highestTemperature: Double = 0.0
    var periods = ArrayList<Period>()

    class Period {
        var date: String = ""
        var temperature: Double = 0.0
        var temperatureDisplay: String = ""
        var weatherDescription: String = ""
        var hour: String = ""
        var weatherIcon: String = ""
        var bitmapIcon: Bitmap?

        constructor() {
            bitmapIcon = null
        }
    }
}