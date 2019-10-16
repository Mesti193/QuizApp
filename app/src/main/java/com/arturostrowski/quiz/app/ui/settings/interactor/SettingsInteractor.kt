package com.arturostrowski.quiz.app.ui.settings.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.database.repository.user.UserRepo
import com.arturostrowski.quiz.app.data.network.ApiHelper
import com.arturostrowski.quiz.app.data.preferences.PreferenceHelper
import com.arturostrowski.quiz.app.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class SettingsInteractor @Inject constructor(private val userRepo: UserRepo, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), SettingsMVPInteractor {

    override fun loadUser(): Observable<List<User>> = userRepo.loadUser()

    override fun logout() = userRepo.logout()

}