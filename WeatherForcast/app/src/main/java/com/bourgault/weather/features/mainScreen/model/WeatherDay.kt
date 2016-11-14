package com.bourgault.weather.features.mainScreen.model

import android.graphics.Bitmap
import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Bean for MainScreen's RecyclerView
 * Created by Yoann on 12/11/2016.
 */
class WeatherDay : Parcelable {

    var city: String? = null
    var date: String? = null
    var lowestTemperature: Int? = null
    var highestTemperature: Int? = null
    var iconId: String? = null
    var day: String? = null
    var description: String? = null
    var periods: MutableList<Period>? = ArrayList()
    var bitmapIcon: Bitmap? = null

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(city ?: "")
        dest?.writeString(date ?: "")
        dest?.writeInt(lowestTemperature ?: 0)
        dest?.writeInt(highestTemperature ?: 0)
        dest?.writeString(iconId ?: "")
        dest?.writeString(day ?: "")
        dest?.writeString(description ?: "")
        dest?.writeTypedList<Period>(periods)
        dest?.writeValue(bitmapIcon)
    }

    override fun describeContents(): Int {
        return 0
    }


    companion object {
        @JvmField @Suppress("unused")
        val CREATOR: Parcelable.Creator<WeatherDay> = object : Parcelable.Creator<WeatherDay> {
            override fun createFromParcel(parcelIn: Parcel): WeatherDay {
                return WeatherDay(parcelIn)
            }

            override fun newArray(size: Int): Array<WeatherDay> {
                return Array(size, { i -> WeatherDay() })
            }
        }
    }
}

private fun WeatherDay(parcelIn: Parcel): WeatherDay {
    val weatherDay = WeatherDay()
    weatherDay.city = parcelIn.readString()
    weatherDay.date = parcelIn.readString()
    weatherDay.lowestTemperature = parcelIn.readInt()
    weatherDay.highestTemperature = parcelIn.readInt()
    weatherDay.iconId = parcelIn.readString()
    weatherDay.day = parcelIn.readString()
    weatherDay.description = parcelIn.readString()
    parcelIn.readTypedList<Period>(weatherDay.periods, Period.CREATOR)
    weatherDay.bitmapIcon = parcelIn.readValue(Bitmap::class.java.classLoader) as Bitmap
    return weatherDay
}
