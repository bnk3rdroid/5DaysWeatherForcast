package bnk3r.droid.weatherforecast.features.splashScreen.ui

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.RelativeLayout
import android.widget.TextView
import bnk3r.droid.weatherforecast.R
import bnk3r.droid.weatherforecast.application.App
import bnk3r.droid.weatherforecast.features.splashScreen.di.DaggerSplashScreenComponent
import bnk3r.droid.weatherforecast.features.splashScreen.di.SplashScreenComponent
import bnk3r.droid.weatherforecast.features.splashScreen.di.SplashScreenModule
import butterknife.BindView
import butterknife.ButterKnife
import javax.inject.Inject

class SplashScreenView
: AppCompatActivity(), SplashScreenContract.View, SplashScreenContract.DaggerComponent {

    /*
    * AppCompatActivity
    * */

    @BindView(R.id.activity_splash_screen)
    lateinit var container: RelativeLayout

    @BindView(R.id.tv)
    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        ButterKnife.bind(this)
        splashScreenMVPComponent()
        presenter.retrieveVersionName()
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

    /*
    * SplashScreenContract.View
    * */

    override fun showVersionName(versionName: String) {
        tv.text = versionName
    }

    /*
    * DaggerComponent
    * */

    @Inject
    lateinit var presenter: SplashScreenContract.Presenter

    lateinit var component: SplashScreenComponent

    override fun splashScreenMVPComponent() {
        component = DaggerSplashScreenComponent.builder()
                .appComponent((application as App).component)
                .splashScreenModule(SplashScreenModule(this))
                .build()
        component.inject(this)
    }

}
