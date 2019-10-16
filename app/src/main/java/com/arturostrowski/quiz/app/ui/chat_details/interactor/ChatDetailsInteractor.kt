package com.arturostrowski.quiz.app.ui.chat_details.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.database.repository.user.UserRepo
import com.arturostrowski.quiz.app.data.network.ApiHelper
import com.arturostrowski.quiz.app.data.network.request.AddChatContentRequest
import com.arturostrowski.quiz.app.data.network.request.GetChatContentRequest
import com.arturostrowski.quiz.app.data.network.response.AddChatContentResponse
import com.arturostrowski.quiz.app.data.network.response.GetChatContentResponse
import com.arturostrowski.quiz.app.data.preferences.PreferenceHelper
import com.arturostrowski.quiz.app.ui.base.interactor.BaseInteractor
import io.reactivex.Observable
import javax.inject.Inject


class ChatDetailsInteractor@Inject internal constructor(private val userRepo: UserRepo, preferenceHelper: PreferenceHelper, apiHelper: ApiHelper) : BaseInteractor(preferenceHelper, apiHelper), ChatDetailsMVPInteractor {

    override fun addChatContent(request: AddChatContentRequest): Observable<AddChatContentResponse> = apiHelper.addChatContent(request)
    override fun getChatContent(request: GetChatContentRequest): Observable<GetChatContentResponse> = apiHelper.getChatContent(request)
    override fun loadUser(): Observable<List<User>> = userRepo.loadUser()

}