package bnk3r.droid.weatherforecast.base

import android.app.Application
import bnk3r.droid.weatherforecast.base.contract.AppContract

/**
 * Base class for every application
 * Created by YomanHD on 01/11/2016.
 */
abstract class BaseApp: Application(), AppContract {

    override fun onCreate() {
        super.onCreate()
        onInject()
    }

}