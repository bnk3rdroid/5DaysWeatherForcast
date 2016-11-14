package com.bourgault.weather.features.mainScreen.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Bean for MainScreen's RecyclerView
 * Created by Yoann on 12/11/2016.
 */
class Period : Parcelable {
    var date: String? = null
    var temperature: Double? = null
    var temperatureDisplay: String? = null
    var weatherDescription: String? = null
    var hour: String? = null
    var weatherIcon: String? = null
    var day: String? = null
    var windSpeed: Double? = null
    var humidity: Int? = null
    var cloudiness: Int? = null
    var pressure_sea: Double? = null
    var pressure_ground: Double? = null

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(date)
        dest?.writeDouble(temperature ?: 0.0)
        dest?.writeString(temperatureDisplay)
        dest?.writeString(weatherDescription)
        dest?.writeString(hour)
        dest?.writeString(weatherIcon)
        dest?.writeString(day)
        dest?.writeDouble(windSpeed ?: 0.0)
        dest?.writeInt(humidity ?: 0)
        dest?.writeInt(cloudiness ?: 0)
        dest?.writeDouble(pressure_sea ?: 0.0)
        dest?.writeDouble(pressure_ground ?: 0.0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object {
        @JvmField @Suppress("unused")
        val CREATOR: Parcelable.Creator<Period> = object : Parcelable.Creator<Period> {
            override fun createFromParcel(parcelIn: Parcel): Period {
                return Period(parcelIn)
            }

            override fun newArray(size: Int): Array<Period> {
                return Array(size, { i -> Period() })
            }
        }
    }
}

private fun Period(parcelIn: Parcel): Period {
    val period = Period()
    period.date = parcelIn.readString()
    period.temperature = parcelIn.readDouble()
    period.temperatureDisplay = parcelIn.readString()
    period.weatherDescription = parcelIn.readString()
    period.hour = parcelIn.readString()
    period.weatherIcon = parcelIn.readString()
    period.day = parcelIn.readString()
    period.windSpeed = parcelIn.readDouble()
    period.humidity = parcelIn.readInt()
    period.cloudiness = parcelIn.readInt()
    period.pressure_sea = parcelIn.readDouble()
    period.pressure_ground = parcelIn.readDouble()
    return period
}