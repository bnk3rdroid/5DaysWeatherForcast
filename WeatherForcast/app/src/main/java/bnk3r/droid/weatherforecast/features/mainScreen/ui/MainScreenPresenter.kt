package bnk3r.droid.weatherforecast.features.mainScreen.ui

import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import bnk3r.droid.weatherforecast.features.mainScreen.model.WeatherDay
import bnk3r.droid.weatherforecast.modules.network.model.WeatherDayObject
import bnk3r.droid.weatherforecast.modules.network.model.WeatherRequestObject
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

/**
 * MVP Presenter
 * Created by YomanHD on 01/11/2016.
 */
class MainScreenPresenter
@Inject
constructor(
        private var view: MainScreenContract.View,
        private var requestQueue: RequestQueue
)
: MainScreenContract.Presenter,
        Response.Listener<JSONObject>, Response.ErrorListener {

    private val KELVIN_RATION = 273.15

    private val parisId: String = "2988507"
    private val token: String = "d61526e65e05d57de232228ca1f9feef"
    private val url: String = "http://api.openweathermap.org/data/2.5/forecast?id=$parisId&APPID=$token"

    private fun parseCityForDisplay(response: JSONObject?): String {
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

    private fun parseWeatherDaysForDisplay(response: JSONObject?, city: String) {
        val weatherDays = ArrayList<WeatherDay>()
        try {
            val weatherRequestObject = Gson().fromJson(response.toString(), WeatherRequestObject::class.java)

            var weatherDay = WeatherDay()
            weatherDay.city = city
            //The date of the first day
            weatherDay.date = SimpleDateFormat("EEE dd MMM yyyy", Locale.FRANCE).format(
                    SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).parse(weatherRequestObject.list[0].dt_txt)
            )
            weatherDays.add(weatherDay)
            //Loop on all periods in the JSON
            for (wdo in weatherRequestObject.list) {
                val date = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.FRANCE).parse(wdo.dt_txt)

                val period = WeatherDay.Period()
                period.date = SimpleDateFormat("EEE dd MMM yyyy", Locale.FRANCE).format(date)
                period.hour = SimpleDateFormat("HH", Locale.FRANCE).format(date)
                if (weatherDay.date != period.date) {
                    weatherDay.lowestTemperature = findLowestTemperature(weatherDay.periods)
                    weatherDay.highestTemperature = findHighestTemperature(weatherDay.periods)
                    weatherDay = WeatherDay()
                    weatherDay.city = city
                    weatherDay.date = period.date
                    weatherDays.add(weatherDay)
                }
                period.temperature = wdo.main.temp - KELVIN_RATION
                period.temperatureDisplay = period.temperature.toString() + "°C"
                period.weatherDescription = wdo.weather[0].description
                period.weatherIcon = wdo.weather[0].icon
                weatherDay.periods.add(period)
            }
        } catch (e: JSONException) {
            Log.e("KO", "error (JSON): ($e)")
            view.hideProgress()
            view.showError("error (JSON): ($e)")
        } catch (e: NullPointerException) {
            Log.e("KO", "error (NPE): ($e)")
            view.hideProgress()
            view.showError("error (NPE): ($e)")
        }
        view.hideProgress()
        view.showWeather(weatherDays)
    }

    fun findLowestTemperature(list: ArrayList<WeatherDay.Period>): Double {
        var lowest = 100.0
        for (period in list) {
            val temp = period.temperature
            if (temp < lowest)
                lowest = temp
        }
        return lowest
    }

    fun findHighestTemperature(list: ArrayList<WeatherDay.Period>): Double {
        var highest = -100.0
        for (period in list) {
            val temp = period.temperature
            if (period.temperature > highest)
                highest = temp
        }
        return highest
    }

    /*
    * MainScreenContract.Presenter
    * */

    override fun findWeather() {
        requestQueue.add(JsonObjectRequest(Request.Method.GET, Uri.parse(url).toString(), null, this, this))
    }

    /*
    * Response.Listener<JSONObject>
    * */
    override fun onResponse(response: JSONObject?) {
        val city = parseCityForDisplay(response)
        view.showCityName(city)
        parseWeatherDaysForDisplay(response, city)
    }

    /*
    * Response.ErrorListener
    * */

    override fun onErrorResponse(error: VolleyError?) {
        Log.e("KO", "error=\n${error.toString()}")
        view.hideProgress()
        view.showError("error=\n${error.toString()}")
    }
}