package com.arturostrowski.quiz.app.ui.splash.interactor

import com.arturostrowski.quiz.app.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface SplashMVPInteractor : MVPInteractor {

    fun isUserLogged(): Observable<Boolean>
}