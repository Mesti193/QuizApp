package com.arturostrowski.quiz.app.ui.chat_details.interactor

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.request.AddChatContentRequest
import com.arturostrowski.quiz.app.data.network.request.GetChatContentRequest
import com.arturostrowski.quiz.app.data.network.response.AddChatContentResponse
import com.arturostrowski.quiz.app.data.network.response.GetChatContentResponse
import com.arturostrowski.quiz.app.ui.base.interactor.MVPInteractor
import io.reactivex.Observable

interface ChatDetailsMVPInteractor: MVPInteractor {

    fun addChatContent(request: AddChatContentRequest): Observable<AddChatContentResponse>
    fun getChatContent(request: GetChatContentRequest): Observable<GetChatContentResponse>
    fun loadUser(): Observable<List<User>>

}