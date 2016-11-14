package com.bourgault.weather.features.detailScreen.ui

import android.graphics.Bitmap
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import butterknife.BindView
import butterknife.ButterKnife
import com.bourgault.weather.R
import com.bourgault.weather.application.App
import com.bourgault.weather.base.BaseActivity
import com.bourgault.weather.detailScreen.di.DetailScreenModule
import com.bourgault.weather.features.detailScreen.di.DaggerDetailScreenComponent
import com.bourgault.weather.features.detailScreen.model.Element
import com.bourgault.weather.features.mainScreen.model.WeatherDay
import java.util.*
import javax.inject.Inject

/**
 * MVP view: DetailScreen
 * Created by Yoann on 12/11/2016.
 */
class DetailScreenView : BaseActivity(), DetailScreenContract.View {

    @Inject
    lateinit var presenter: DetailScreenContract.Presenter

    @BindView(R.id.weather_detail_icon)
    lateinit var iconImage: ImageView

    @BindView(R.id.weather_detail_rv)
    lateinit var rv: RecyclerView

    var weatherDay: WeatherDay? = null

    fun init() {
        supportActionBar!!.title = "Paris"
        val bundle = intent?.extras
        weatherDay = bundle?.getParcelable("bundle_weather")
        presenter.formatDay(weatherDay)
    }

    /*
    * BaseActivity
    * */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ButterKnife.bind(this)
        init()
    }

    override fun onInject() {
        DaggerDetailScreenComponent.builder()
                .appComponent((application as App).appComponent)
                .detailScreenModule(DetailScreenModule(this))
                .build()
                .inject(this)
    }

    /*
    * DetailScreenContract.View
    * */

    override fun setIcon(icon: Bitmap?) {
        iconImage.setImageBitmap(icon)
    }

    override fun dayFormatted(elements: ArrayList<Element>) {
        rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv.adapter = DetailScreenAdapter(elements)
    }

}

