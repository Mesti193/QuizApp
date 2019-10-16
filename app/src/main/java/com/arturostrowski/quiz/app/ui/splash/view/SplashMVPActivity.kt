package com.arturostrowski.quiz.app.ui.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.arturostrowski.quiz.app.R
import com.arturostrowski.quiz.app.ui.base.view.BaseActivity
import com.arturostrowski.quiz.app.ui.main.view.MainActivity
import com.arturostrowski.quiz.app.ui.splash.interactor.SplashMVPInteractor
import com.arturostrowski.quiz.app.ui.splash.presenter.SplashMVPPresenter
import javax.inject.Inject

class SplashMVPActivity : BaseActivity(), SplashMVPView {

    @Inject
    lateinit var presenter: SplashMVPPresenter<SplashMVPView, SplashMVPInteractor>

    private var SPLASH_DISPLAY_LENGTH = 800L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        presenter.onAttach(this)
    }

    override fun onDestroy() {
        presenter.onDetach()
        super.onDestroy()
    }

    override fun onFragmentDetached(tag: String) {
    }

    override fun onFragmentAttached() {
    }

    override fun showSuccessToast() {
    }

    override fun showErrorToast() {
    }

    override fun openMainActivity() {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("isUserLogged", true)
            startActivity(intent)
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }

    override fun openLoginActivity() {
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("isUserLogged", false)
            startActivity(intent)
            finish()
        }, SPLASH_DISPLAY_LENGTH)
    }
}
