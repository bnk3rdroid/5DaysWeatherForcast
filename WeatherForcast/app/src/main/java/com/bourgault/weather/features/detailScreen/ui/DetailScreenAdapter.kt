package com.bourgault.weather.features.detailScreen.ui

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bourgault.weather.R
import com.bourgault.weather.features.detailScreen.model.Element
import kotlinx.android.synthetic.main.item_weather_detail.view.*
import java.util.*

/**
 * Adapter: DetailScreenView.RecyclerView
 * Created by Yoann on 12/11/2016.
 */
class DetailScreenAdapter(
        private val data: ArrayList<Element>
) : RecyclerView.Adapter<DetailScreenAdapter.VH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailScreenAdapter.VH {
        val context = parent.context
        val layout = R.layout.item_weather_detail
        return DetailScreenAdapter.VH(LayoutInflater.from(context).inflate(layout, parent, false), context)
    }

    override fun onBindViewHolder(holder: DetailScreenAdapter.VH, position: Int) {
        holder.bindElement(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class VH(view: View, private val context: Context) : RecyclerView.ViewHolder(view)  {

        fun bindElement(element: Element) {
            with(element) {
                itemView.weather_detail_item_title.text = element.title
                itemView.weather_detail_item_value.text = element.value
            }
        }

    }

}