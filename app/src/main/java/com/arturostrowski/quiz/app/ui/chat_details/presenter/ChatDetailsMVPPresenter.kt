package com.arturostrowski.quiz.app.ui.chat_details.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.Chat
import com.arturostrowski.quiz.app.ui.base.presenter.MVPPresenter
import com.arturostrowski.quiz.app.ui.chat_details.interactor.ChatDetailsMVPInteractor
import com.arturostrowski.quiz.app.ui.chat_details.view.ChatDetailsMVPView

interface ChatDetailsMVPPresenter<V : ChatDetailsMVPView, I : ChatDetailsMVPInteractor> : MVPPresenter<V, I> {

    fun onAddedChatContent(user: User, chat: Chat, message: String, timestamp: Long)
    fun onChatContentPrepared(user: User, chat: Chat, timestamp: Long)
    fun loadUserFromDatabase()

}