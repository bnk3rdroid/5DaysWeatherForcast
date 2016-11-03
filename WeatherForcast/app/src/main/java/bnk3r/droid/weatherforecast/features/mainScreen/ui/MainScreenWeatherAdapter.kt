package bnk3r.droid.weatherforecast.features.mainScreen.ui

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bnk3r.droid.weatherforecast.R
import bnk3r.droid.weatherforecast.features.mainScreen.model.WeatherDay
import kotlinx.android.synthetic.main.item_weather_day.view.*

/**
 * Adapter for MainScreen's RecyclerView
 * Created by YomanHD on 03/11/2016.
 */
class MainScreenWeatherAdapter(private val weatherDays: List<WeatherDay>)
: RecyclerView.Adapter<MainScreenWeatherAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val context = parent.context
        return VH(LayoutInflater.from(context).inflate(R.layout.item_weather_day, parent, false), context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindWeatherDay(weatherDays[position], position)
    }

    override fun getItemCount(): Int {
        return weatherDays.size
    }

    class VH(view: View, private val context: Context): RecyclerView.ViewHolder(view) {
        fun bindWeatherDay(weatherDay: WeatherDay, position: Int) {
            with(weatherDay) {
                if (position % 2 == 0) {
                    val color = ContextCompat.getColor(context, android.R.color.holo_orange_light)
                    itemView.item_weather_day_container.setBackgroundColor(color)
                }
            }
        }
    }
}