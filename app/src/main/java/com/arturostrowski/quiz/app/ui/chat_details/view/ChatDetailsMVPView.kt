package com.arturostrowski.quiz.app.ui.chat_details.view

import com.arturostrowski.quiz.app.data.database.repository.user.User
import com.arturostrowski.quiz.app.data.network.response.ChatContent
import com.arturostrowski.quiz.app.data.network.response.UserDetail
import com.arturostrowski.quiz.app.ui.base.view.MVPView

interface ChatDetailsMVPView: MVPView {

    fun displayUser(user: UserDetail)
    fun displayChatContent(chatContent: ArrayList<ChatContent>, timestamp: Long)
    fun getUser(user: User)
    fun displayError(error: String)

}