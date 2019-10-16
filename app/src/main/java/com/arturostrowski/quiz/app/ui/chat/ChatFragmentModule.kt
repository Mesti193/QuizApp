package com.arturostrowski.quiz.app.ui.chat

import com.arturostrowski.quiz.app.ui.chat.interactor.ChatInteractor
import com.arturostrowski.quiz.app.ui.chat.interactor.ChatMVPInteractor
import com.arturostrowski.quiz.app.ui.chat.presenter.ChatMVPPresenter
import com.arturostrowski.quiz.app.ui.chat.presenter.ChatPresenter
import com.arturostrowski.quiz.app.ui.chat.view.ChatFragment
import com.arturostrowski.quiz.app.ui.chat.view.ChatMVPView
import dagger.Module
import dagger.Provides

@Module
class ChatFragmentModule {

    @Provides
    internal fun provideChatInteractor(interactor: ChatInteractor): ChatMVPInteractor = interactor

    @Provides
    internal fun provideChatPresenter(presenter: ChatPresenter<ChatMVPView, ChatMVPInteractor>)
            : ChatMVPPresenter<ChatMVPView, ChatMVPInteractor> = presenter


    @Provides
    internal fun provideLinearLayoutManager(fragment: ChatFragment): androidx.recyclerview.widget.LinearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(fragment.activity)
}