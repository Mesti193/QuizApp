package com.arturostrowski.quiz.app.ui.main.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface MainMVPInteractor : MVPInteractor {

    fun getUserDetails() : Pair<String?,String?>
    fun loadUser(): Observable<List<User>>
    fun logout(): Observable<Boolean>

}