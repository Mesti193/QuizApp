package com.arturostrowski.quiz.app.ui.chat.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.database.repository.user.UserRepo
import com.arturostrowski.quiz.app.data.network.ApiHelper
import com.arturostrowski.quiz.app.data.network.request.GetChatRequest
import com.arturostrowski.quiz.app.data.network.response.GetChatResponse
import com.arturostrowski.quiz.app.data.preferences.PreferenceHelper
import com.arturostrowski.quiz.app.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject

class ChatInteractor @Inject internal constructor(private val userRepo: UserRepo, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), ChatMVPInteractor {

    override fun getChats(request: GetChatRequest): Observable<GetChatResponse> = apiHelper.getChats(request)
    override fun loadUser(): Observable<List<User>> = userRepo.loadUser()

}