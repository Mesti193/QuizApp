package com.arturostrowski.quiz.app.ui.base.view

import com.arturostrowski.quiz.app.data.network.response.ChatContent

interface ChatViewHolder {
    fun bindViews(chatContent: ChatContent)
}