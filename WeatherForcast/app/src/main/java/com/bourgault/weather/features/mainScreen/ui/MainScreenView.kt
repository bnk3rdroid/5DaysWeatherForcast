package com.bourgault.weather.features.mainScreen.ui

import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bourgault.weather.R
import com.bourgault.weather.application.App
import com.bourgault.weather.base.BaseActivity
import com.bourgault.weather.features.mainScreen.di.DaggerMainScreenComponent
import com.bourgault.weather.features.mainScreen.di.MainScreenComponent
import com.bourgault.weather.features.mainScreen.di.MainScreenModule
import com.bourgault.weather.features.mainScreen.model.WeatherDay
import javax.inject.Inject
import android.widget.Toast



/**
 * MVP view: MainScreen
 * Created by Yoann on 12/11/2016.
 */
class MainScreenView : BaseActivity(), MainScreenContract.View {

    var doubleBackToExitPressedOnce = false

    fun init() {
        supportActionBar!!.title = "Seek weather for..."
        presenter.findWeather()
    }

    /*
    * BaseActivity
    * */

    @BindView(R.id.weather_rv)
    lateinit var weatherRV: RecyclerView

    @BindView(R.id.main_screen_progress_container)
    lateinit var loading: RelativeLayout

    @BindView(R.id.main_screen_error_message_container)
    lateinit var errorContainer: LinearLayout

    @BindView(R.id.main_screen_error_message)
    lateinit var errorMessageTV: TextView

    @Inject
    lateinit var adapter: MainScreenWeatherAdapter

    @Inject
    lateinit var presenter: MainScreenContract.Presenter

    lateinit var mainComponent: MainScreenComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen_view)
        ButterKnife.bind(this)
        init()
    }

    override fun onInject() {
        mainComponent = DaggerMainScreenComponent.builder()
                .networkComponent((application as App).networkComponent)
                .mainScreenModule(MainScreenModule(this))
                .build()
        mainComponent.inject(this)
    }

    /*
    * MainScreenContract.View
    * */

    override fun showProgress() {
        hideError()
        hideWeather()
        loading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loading.visibility = View.GONE
    }

    override fun showWeather(weatherDays: List<WeatherDay>) {
        hideError()
        hideProgress()
        weatherRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        weatherRV.adapter = adapter
        (weatherRV.adapter as MainScreenWeatherAdapter).update(weatherDays)
        weatherRV.visibility = View.VISIBLE
    }

    override fun hideWeather() {
        weatherRV.layoutManager = null
        weatherRV.adapter = null
        weatherRV.visibility = View.GONE
    }

    override fun showError(errorMessage: String) {
        hideWeather()
        hideProgress()
        errorMessageTV.text = errorMessage
        errorContainer.visibility = View.VISIBLE
        errorContainer.setOnClickListener {
            presenter.findWeather()
        }
    }

    override fun hideError() {
        errorContainer.visibility = View.GONE
    }

    override fun showCityName(city: String) {
        supportActionBar!!.title = city
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, getString(R.string.exit_double_tap), Toast.LENGTH_SHORT).show()

        Handler().postDelayed({
                    doubleBackToExitPressedOnce = false
                }, 2000
        )
    }
}
