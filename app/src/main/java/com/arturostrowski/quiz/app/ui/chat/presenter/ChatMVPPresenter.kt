package com.arturostrowski.quiz.app.ui.chat.presenter

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.ui.base.presenter.MVPPresenter
import com.arturostrowski.quiz.app.ui.chat.interactor.ChatMVPInteractor
import com.arturostrowski.quiz.app.ui.chat.view.ChatMVPView


interface ChatMVPPresenter<V : ChatMVPView, I : ChatMVPInteractor> : MVPPresenter<V, I> {

    fun onChatsPrepared(user: User)
    fun loadUserFromDatabase()

}