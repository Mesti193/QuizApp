package com.arturostrowski.quiz.app.ui.chat

import com.arturostrowski.quiz.app.ui.chat.view.ChatFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ChatFragmentProvider {

    @ContributesAndroidInjector(modules = [(ChatFragmentModule::class)])
    internal abstract fun provideChatFragmentFactory(): ChatFragment

}