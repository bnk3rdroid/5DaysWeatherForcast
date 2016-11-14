package com.bourgault.weather.features.mainScreen.ui

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import com.bourgault.weather.R
import com.bourgault.weather.features.detailScreen.ui.DetailScreenView
import com.bourgault.weather.features.mainScreen.model.WeatherDay
import kotlinx.android.synthetic.main.item_weather_day.view.*
import java.util.*
import javax.inject.Inject

/**
 * Adapter for MainScreen's RecyclerView
 * Created by Yoann on 12/11/2016.
 */
class MainScreenWeatherAdapter
@Inject
constructor(
        private val requestQueue: RequestQueue
) : RecyclerView.Adapter<MainScreenWeatherAdapter.VH>() {

    private var weatherDays: List<WeatherDay> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val context = parent.context
        val layout = R.layout.item_weather_day
        return VH(LayoutInflater.from(context).inflate(layout, parent, false), context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindWeatherDay(requestQueue, weatherDays[position])
    }

    override fun getItemCount(): Int {
        return weatherDays.size
    }

    fun update(weatherDays: List<WeatherDay>) {
        this.weatherDays = weatherDays
        notifyDataSetChanged()
    }

    class VH(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {

        fun bindWeatherDay(requestQueue: RequestQueue, weatherDay: WeatherDay) {
            with(weatherDay) {
                itemView.card_weather_title.text = weatherDay.day

                val temp = "${weatherDay.highestTemperature}° / ${weatherDay.lowestTemperature}°"
                itemView.card_weather_temperatures.text = temp

                //Request icon
                val id = weatherDay.iconId
                val url = "http://openweathermap.org/img/w/$id.png"
                val imageRequest = ImageRequest(url,
                        object : Response.Listener<Bitmap> {
                            override fun onResponse(bitmap: Bitmap?) {
                                weatherDay.bitmapIcon = bitmap
                                itemView.card_weather_icon.setImageBitmap(bitmap)
                            }
                        }, 0, 0, null, Bitmap.Config.ARGB_8888,
                        object : Response.ErrorListener {
                            override fun onErrorResponse(error: VolleyError) {
                                Log.e("KO", "Error=\n$error")
                            }
                        })
                requestQueue.add(imageRequest)

                itemView.card_weather_container.setOnClickListener {
                    val intent = Intent(context, DetailScreenView::class.java)
                    intent.putExtra("bundle_weather", weatherDay)
                    context.startActivity(intent)
                }
            }
        }
    }
}