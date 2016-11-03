package bnk3r.droid.weatherforecast.features.mainScreen.ui

import android.os.Bundle
import android.widget.Toast
import bnk3r.droid.weatherforecast.R
import bnk3r.droid.weatherforecast.application.App
import bnk3r.droid.weatherforecast.base.BaseActivity
import bnk3r.droid.weatherforecast.features.mainScreen.di.DaggerMainScreenComponent
import bnk3r.droid.weatherforecast.features.mainScreen.di.MainScreenComponent
import bnk3r.droid.weatherforecast.features.mainScreen.di.MainScreenModule
import butterknife.ButterKnife
import javax.inject.Inject

class MainScreenView : BaseActivity(), MainScreenContract.View {

    /*
    * BaseActivity
    * */

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


}
