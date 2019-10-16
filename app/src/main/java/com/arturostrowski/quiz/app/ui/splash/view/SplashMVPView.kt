package com.arturostrowski.quiz.app.ui.splash.view

import com.arturostrowski.quiz.app.ui.base.view.MVPView

interface SplashMVPView : MVPView {

    fun showSuccessToast()
    fun showErrorToast()
    fun openMainActivity()
    fun openLoginActivity()
}