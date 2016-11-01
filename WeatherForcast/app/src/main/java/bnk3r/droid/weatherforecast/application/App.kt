package bnk3r.droid.weatherforecast.application

import bnk3r.droid.weatherforecast.application.di.AppComponent
import bnk3r.droid.weatherforecast.application.di.AppModule
import bnk3r.droid.weatherforecast.application.di.DaggerAppComponent
import bnk3r.droid.weatherforecast.base.BaseApp
import bnk3r.droid.weatherforecast.base.contract.AppContract

/**
 * Application class
 * Created by YomanHD on 01/11/2016.
 */
class App: BaseApp(), AppContract {

    lateinit var component: AppComponent

    /*
    * AppContract
    * */

    override fun onInject() {
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

}