package bnk3r.droid.weatherforecast.features.mainScreen.ui

import android.content.Context
import android.graphics.Bitmap
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bnk3r.droid.weatherforecast.R
import bnk3r.droid.weatherforecast.features.mainScreen.model.WeatherDay
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.ImageRequest
import kotlinx.android.synthetic.main.item_weather_day.view.*
import java.util.*
import javax.inject.Inject

/**
 * Adapter for MainScreen's RecyclerView
 * Created by YomanHD on 03/11/2016.
 */
class MainScreenWeatherAdapter
@Inject
constructor(
        private val requestQueue: RequestQueue
)
: RecyclerView.Adapter<MainScreenWeatherAdapter.VH>() {

    private var weatherDays: List<WeatherDay> = ArrayList<WeatherDay>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val context = parent.context
        val layout = R.layout.item_weather_day
        return VH(LayoutInflater.from(context).inflate(layout, parent, false), context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindWeatherDay(requestQueue, weatherDays[position], position)
    }

    override fun getItemCount(): Int {
        return weatherDays.size
    }

    fun update(weatherDays: List<WeatherDay>) {
        this.weatherDays = weatherDays
        notifyDataSetChanged()
    }

    class VH(view: View, private val context: Context) : RecyclerView.ViewHolder(view) {
        //To format the temperatures
        fun Double.format() = java.lang.String.format("%.${1}f", this)

        fun bindWeatherDay(requestQueue: RequestQueue, weatherDay: WeatherDay, position: Int) {
            with(weatherDay) {
                if (position % 2 == 0) {
                    val color = ContextCompat.getColor(context, R.color.colorPrimaryDark)
                    itemView.item_weather_day_container.setBackgroundColor(color)
                }
                itemView.iwd_date.text = weatherDay.date
                itemView.iwd_lowest_temperature.text = "min: ${weatherDay.lowestTemperature.format()}°C"
                itemView.iwd_highest_temperature.text = "max: ${weatherDay.highestTemperature.format()}°C"

                //Request icon
                val mid = weatherDay.periods.size / 2
                val id = weatherDay.periods[mid].weatherIcon
                val url = "http://openweathermap.org/img/w/$id.png"
                val imageRequest = ImageRequest(url,
                        object : Response.Listener<Bitmap> {
                            override fun onResponse(bitmap: Bitmap?) {
                                itemView.iwd_icon.setImageBitmap(bitmap)
                            }
                        }, 0, 0, null, Bitmap.Config.ARGB_8888,
                        object : Response.ErrorListener {
                            override fun onErrorResponse(error: VolleyError) {
                                Log.e("KO", "Error=\n$error")
                            }
                        })
                requestQueue.add(imageRequest)
            }
        }
    }
}