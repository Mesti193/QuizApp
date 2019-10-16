package com.arturostrowski.quiz.app.ui.base.interactor

interface MVPInteractor {

    fun isUserLoggedIn(): Boolean

    fun performUserLogout()

}