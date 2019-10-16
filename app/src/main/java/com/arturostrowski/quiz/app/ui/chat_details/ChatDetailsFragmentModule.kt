package com.arturostrowski.quiz.app.ui.chat_details

import com.arturostrowski.quiz.app.ui.chat_details.interactor.ChatDetailsInteractor
import com.arturostrowski.quiz.app.ui.chat_details.interactor.ChatDetailsMVPInteractor
import com.arturostrowski.quiz.app.ui.chat_details.presenter.ChatDetailsMVPPresenter
import com.arturostrowski.quiz.app.ui.chat_details.presenter.ChatDetailsPresenter
import com.arturostrowski.quiz.app.ui.chat_details.view.ChatDetailsFragment
import com.arturostrowski.quiz.app.ui.chat_details.view.ChatDetailsMVPView
import dagger.Module
import dagger.Provides

@Module
class ChatDetailsFragmentModule {

    @Provides
    internal fun provideChatDetailsInteractor(interactor: ChatDetailsInteractor): ChatDetailsMVPInteractor = interactor

    @Provides
    internal fun provideChatDetailsPresenter(presenter: ChatDetailsPresenter<ChatDetailsMVPView, ChatDetailsMVPInteractor>)
            : ChatDetailsMVPPresenter<ChatDetailsMVPView, ChatDetailsMVPInteractor> = presenter


    @Provides
    internal fun provideLinearLayoutManager(fragment: ChatDetailsFragment): androidx.recyclerview.widget.LinearLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(fragment.activity)
}