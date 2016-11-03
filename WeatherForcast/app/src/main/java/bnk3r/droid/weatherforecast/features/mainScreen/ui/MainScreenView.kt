package bnk3r.droid.weatherforecast.features.mainScreen.ui

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.transition.Visibility
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import android.widget.TextView
import bnk3r.droid.weatherforecast.R
import bnk3r.droid.weatherforecast.application.App
import bnk3r.droid.weatherforecast.base.BaseActivity
import bnk3r.droid.weatherforecast.features.mainScreen.di.DaggerMainScreenComponent
import bnk3r.droid.weatherforecast.features.mainScreen.di.MainScreenComponent
import bnk3r.droid.weatherforecast.features.mainScreen.di.MainScreenModule
import bnk3r.droid.weatherforecast.features.mainScreen.model.WeatherDay
import butterknife.BindView
import butterknife.ButterKnife
import javax.inject.Inject

class MainScreenView : BaseActivity(), MainScreenContract.View {

    /*
    * BaseActivity
    * */

    @BindView(R.id.weather_rv)
    lateinit var weatherRV: RecyclerView

    @BindView(R.id.main_screen_progress_container)
    lateinit var loading: RelativeLayout

    @BindView(R.id.main_screen_error_message)
    lateinit var errorMessageTV: TextView

    @Inject
    lateinit var presenter: MainScreenContract.Presenter

    lateinit var mainComponent: MainScreenComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_screen_view)
        ButterKnife.bind(this)
        presenter.findWeather()
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
        loading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        loading.visibility = View.GONE
    }

    override fun showWeather(weatherDays: List<WeatherDay>) {
        weatherRV.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        weatherRV.adapter = MainScreenWeatherAdapter(weatherDays)
        weatherRV.visibility = View.VISIBLE
    }

    override fun hideWeather() {
        weatherRV.layoutManager = null
        weatherRV.adapter = null
        weatherRV.visibility = View.GONE
    }

    override fun showError(errorMessage: String) {
        errorMessageTV.text = errorMessage
        errorMessageTV.visibility = View.VISIBLE
    }

    override fun hideError() {
        errorMessageTV.visibility = View.GONE
    }
}
