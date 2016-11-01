package bnk3r.droid.weatherforecast.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import bnk3r.droid.weatherforecast.base.contract.ActivityContract

/**
 * Base class for every activity
 * Created by YomanHD on 01/11/2016.
 */
abstract class BaseActivity : AppCompatActivity(), ActivityContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInject()
    }
}