package com.arturostrowski.quiz.app.ui.login.view

import com.arturostrowski.quiz.app.ui.base.view.MVPView

interface LoginMVPView : MVPView {

    fun openMainFragment()
    fun showValidationMessage(stringRes: Int)
    fun displayError(error: String)

}