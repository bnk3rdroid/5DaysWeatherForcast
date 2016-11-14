package com.bourgault.weather.features.splashScreen.ui

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bourgault.weather.R
import com.bourgault.weather.application.App
import com.bourgault.weather.base.BaseActivity
import com.bourgault.weather.features.mainScreen.ui.MainScreenView
import com.bourgault.weather.features.splashScreen.di.DaggerSplashScreenComponent
import com.bourgault.weather.features.splashScreen.di.SplashScreenComponent
import com.bourgault.weather.features.splashScreen.di.SplashScreenModule
import javax.inject.Inject

/**
 * MVP view: SplashScreen
 * Created by Yoann on 12/11/2016.
 */
class SplashScreenView
: BaseActivity(), SplashScreenContract.View {

    /*
    * BaseActivity
    * */

    @BindView(R.id.activity_splash_screen)
    lateinit var container: RelativeLayout

    @BindView(R.id.tv)
    lateinit var tv: TextView

    @Inject
    lateinit var presenter: SplashScreenContract.Presenter

    lateinit var component: SplashScreenComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        ButterKnife.bind(this)
        presenter.retrieveVersionName()
        finishSplashScreen()
    }

    override fun onInject() {
        component = DaggerSplashScreenComponent.builder()
                .appComponent((application as App).appComponent)
                .splashScreenModule(SplashScreenModule(this))
                .build()
        component.inject(this)
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            container.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
    }

    private fun finishSplashScreen() {
        Thread(Runnable {
            kotlin.run {
                try {
                    Thread.sleep(1500)
                } catch (e: InterruptedException) {
                    //we don't do anything, we simply go faster to the main screen
                }
                val intent = Intent(this, MainScreenView::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }).start()
    }

    /*
    * SplashScreenContract.View
    * */

    override fun showVersionName(versionName: String) {
        tv.text = versionName
    }

}
