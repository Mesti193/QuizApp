package com.arturostrowski.quiz.app.ui.splash.interactor

import com.arturostrowski.quiz.app.data.database.repository.questions.Question
import com.arturostrowski.quiz.app.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface SplashMVPInteractor : MVPInteractor {

    fun seedQuestions(): Observable<Boolean>
    fun isUserLogged(): Observable<Boolean>
    fun seedOptions(): Observable<Boolean>
    fun getQuestion() : Observable<List<Question>>
}