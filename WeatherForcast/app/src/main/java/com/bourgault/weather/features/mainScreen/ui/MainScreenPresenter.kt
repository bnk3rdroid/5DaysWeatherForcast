package com.bourgault.weather.features.mainScreen.ui

import android.app.Application
import android.net.Uri
import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.bourgault.weather.R
import com.bourgault.weather.modules.network.model.WeatherRequestObject
import com.google.gson.Gson
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject

/**
 * MVP Presenter: MainScreen
 * Created by Yoann on 12/11/2016.
 */
class MainScreenPresenter
@Inject
constructor(
        private var view: MainScreenContract.View,
        private var context: Application,
        private var requestQueue: RequestQueue
) : MainScreenContract.Presenter, Response.Listener<JSONObject>, Response.ErrorListener {

    /*
    * MainScreenContract.Presenter
    * */

    override fun findWeather() {
        view.showProgress()
        var url = context.getString(R.string.root_url)
        url += context.getString(R.string.url_forecast)
        url += context.getString(R.string.param_id_paris)
        url += context.getString(R.string.param_token)
        requestQueue.add(JsonObjectRequest(Request.Method.GET, Uri.parse(url).toString(), null, this, this))
    }

    /*
    * Response.Listener<JSONObject>
    * */
    override fun onResponse(response: JSONObject?) {
        //Extracting the city name for display
        view.showCityName(MainScreenConverter.instance.parseCityForDisplay(response))
        try {
            //Json to POJO using Gson library
            val weatherRequestObject = Gson().fromJson(response.toString(), WeatherRequestObject::class.java)
            //At this point weatherRequestObject.list is full of periods of the next 5 days
            //I need to transform this list in a list of days instead
            val periods = MainScreenConverter.instance.periodsFromRequestObject(weatherRequestObject)
            val weatherDays = MainScreenConverter.instance.periodsToListOfDays(periods)
            //Everything is done I send the list to the view
            view.showWeather(weatherDays)
        } catch (e: JSONException) {
            Log.e("KO", "error (JSON): ($e)")
            view.showError("error (JSON): ($e)")
        } catch (e: NullPointerException) {
            Log.e("KO", "error (NPE): ($e)")
            view.showError("error (NPE): ($e)")
        }
    }

    /*
    * Response.ErrorListener
    * */

    override fun onErrorResponse(error: VolleyError?) {
        Log.e("KO", "error=\n${error.toString()}")
        view.showError("Pas de connexion internet")
    }

}