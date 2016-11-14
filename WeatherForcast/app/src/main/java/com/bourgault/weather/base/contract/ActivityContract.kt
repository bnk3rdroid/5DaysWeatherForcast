package com.bourgault.weather.base.contract

/**
 * Contract for every activity
 * Created by Yoann on 12/11/2016.
 */
interface ActivityContract {
    /**
     * Override to do Dagger2 Component instantiation
     */
    fun onInject()
}