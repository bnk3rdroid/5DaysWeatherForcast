package com.bourgault.weather.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bourgault.weather.base.contract.ActivityContract

/**
 * Base class for every activity
 * Created by Yoann on 12/11/2016.
 */
abstract class BaseActivity : AppCompatActivity(), ActivityContract {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onInject()
    }
}