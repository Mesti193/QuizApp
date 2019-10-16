package com.arturostrowski.quiz.app.ui.chat.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.GetChatRequest
import com.arturostrowski.quiz.app.data.network.response.GetChatResponse
import com.arturostrowski.quiz.app.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface ChatMVPInteractor : MVPInteractor {

    fun getChats(request: GetChatRequest): Observable<GetChatResponse>
    fun loadUser(): Observable<List<User>>

}