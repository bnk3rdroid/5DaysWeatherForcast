package com.bourgault.weather.features.mainScreen.ui

import android.util.Log
import com.bourgault.weather.features.mainScreen.model.Period
import com.bourgault.weather.features.mainScreen.model.WeatherDay
import com.bourgault.weather.modules.network.model.WeatherRequestObject
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*

/**
 * Helper to parse request object
 * Created by Yoann on 12/11/2016.
 */
class MainScreenConverter private constructor() {

    //Singleton pattern
    private object Holder { val INSTANCE = MainScreenConverter() }
    companion object {
        val instance: MainScreenConverter by lazy { Holder.INSTANCE }
    }

    //1°C = 273.15°K (Ratio is just -273.15)
    private val KELVIN_RATION = 273.15
    private val kelvinToCelsius = { x: Double -> x - KELVIN_RATION }

    //Lambdas to format / parse date object
    private val formatDate = { x: Date -> SimpleDateFormat("EEE dd MMM yyyy", Locale.FRANCE).format(x) }
    private val formatDay = { x: Date -> SimpleDateFormat("EEEE", Locale.FRANCE).format(x) }
    private val parseDate = { x: String -> SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).parse(x) }
    private val formatHour = { x: Date -> SimpleDateFormat("HH", Locale.FRANCE).format(x) }

    //Extracts the City name from the JSONObject
    fun parseCityForDisplay(response: JSONObject?): String {
        try {
            return response?.getJSONObject("city")?.getString("name") ?: "Unknown"
        } catch (e: JSONException) {
            Log.e("KO", "error (JSON): ($e)")
            return "Unknown"
        } catch (e: NullPointerException) {
            Log.e("KO", "error (NPE): ($e)")
            return "Unknown"
        }
    }

    //Transforms WeatherRequestObject's list into a list of Period
    fun periodsFromRequestObject(obj: WeatherRequestObject): ArrayList<Period> {
        val periods = ArrayList<Period>()
        for (wdo in obj.list) {
            val period = Period()
            val date = parseDate(wdo.dt_txt)
            period.date = formatDate(date)
            period.day = formatDay(date)
            period.hour = formatHour(date)
            period.temperature = kelvinToCelsius(wdo.main.temp)
            period.temperatureDisplay = period.temperature.toString() + "°C"
            period.weatherDescription = wdo.weather[0].description
            period.windSpeed = wdo.wind.speed
            period.humidity = wdo.main.humidity
            period.cloudiness = wdo.clouds.all
            period.pressure_sea = wdo.main.pressure
            period.pressure_ground = wdo.main.grnd_level
            if (!wdo.weather.isEmpty()) {
                period.weatherDescription = wdo.weather[0].description
                period.weatherIcon = wdo.weather[0].icon
            }
            periods.add(period)
        }
        return periods
    }

    //Transforms a list of Period into a list of day (WeatherDay)
    fun periodsToListOfDays(periods: ArrayList<Period>): ArrayList<WeatherDay> {
        val weatherDays = ArrayList<WeatherDay>()
        if (periods.isEmpty()) return weatherDays
        var weatherDay = WeatherDay()
        var currentDate = periods[0].date
        for (period in periods) {
            //I call it a day when the date change
            if (currentDate != period.date) {
                //I call it a day...
                concludeDay(weatherDay, currentDate ?: "")
                weatherDays.add(weatherDay)
                //...and it's a brand new one \o/
                weatherDay = WeatherDay()
                currentDate = period.date
            } else
                weatherDay.periods?.add(period)
        }
        //For the last day
        concludeDay(weatherDay, currentDate ?: "")
        weatherDays.add(weatherDay)
        return weatherDays
    }

    //Gathering everything to finish WeatherDay object
    fun concludeDay(weatherDay: WeatherDay, date: String) {
        //Floor for lowest and ceil for the highest
        var lowest: Double = 100.0
        var highest: Double = -100.0
        var i: Int = 0
        while (i < weatherDay.periods?.size as Int) {
            val temp = weatherDay.periods!![i].temperature ?: 0.0
            if (temp < lowest)
                lowest = temp
            if (temp > highest)
                highest = temp
            ++i
        }
        weatherDay.lowestTemperature = Math.floor(lowest).toInt()
        weatherDay.highestTemperature = Math.ceil(highest).toInt()
        //Icon for the day is the one at 12h or the 1st icon in the list for the 1st day
        weatherDay.iconId = weatherDay.periods?.firstOrNull { it.hour == "12" }?.weatherIcon
                ?: weatherDay.periods!![weatherDay.periods!!.size / 2].weatherIcon
        weatherDay.date = date
        val day = weatherDay.periods!![0].day
        weatherDay.day = day?.substring(0, 1)?.toUpperCase() + day?.substring(1)
        weatherDay.description = weatherDay.periods!![0].weatherDescription
    }
}