package com.arturostrowski.quiz.app.ui.settings.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface SettingsMVPInteractor : MVPInteractor {

    fun loadUser(): Observable<List<User>>
    fun logout(): Observable<Boolean>

}