package com.arturostrowski.quiz.app.ui.login.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.presenter.MVPPresenter
import com.arturostrowski.quiz.app.ui.login.interactor.LoginMVPInteractor
import com.arturostrowski.quiz.app.ui.login.view.LoginMVPView

interface LoginMVPPresenter<V : LoginMVPView, I : LoginMVPInteractor> : MVPPresenter<V, I> {

    fun onServerLoginClicked(email: String, password: String)

    fun inserUserToDatabase(user: User)

}