package com.arturostrowski.quiz.app.ui.chat.view

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.Chat
import com.arturostrowski.quiz.app.ui.base.view.MVPView

interface ChatMVPView : MVPView {

    fun displayChats(chats: ArrayList<Chat>)
    fun getUser(user: User)
    fun displayError(error: String)

}