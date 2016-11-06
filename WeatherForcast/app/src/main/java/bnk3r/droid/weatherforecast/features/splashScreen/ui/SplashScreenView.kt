package bnk3r.droid.weatherforecast.features.splashScreen.ui

import android.annotation.TargetApi
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.TextView
import bnk3r.droid.weatherforecast.R
import bnk3r.droid.weatherforecast.application.App
import bnk3r.droid.weatherforecast.base.BaseActivity
import bnk3r.droid.weatherforecast.features.mainScreen.ui.MainScreenView
import bnk3r.droid.weatherforecast.features.splashScreen.di.DaggerSplashScreenComponent
import bnk3r.droid.weatherforecast.features.splashScreen.di.SplashScreenComponent
import bnk3r.droid.weatherforecast.features.splashScreen.di.SplashScreenModule
import butterknife.BindView
import butterknife.ButterKnife
import javax.inject.Inject

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

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
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
                startActivity(Intent(this, MainScreenView::class.java))
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
